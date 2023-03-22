package ru.kosterror.sportteamapi.service.teammember;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kosterror.sportteamapi.dto.teammember.BasicTeamMemberDto;
import ru.kosterror.sportteamapi.dto.teammember.CreateUpdateTeamMemberDto;
import ru.kosterror.sportteamapi.dto.teammember.MoveMemberDto;
import ru.kosterror.sportteamapi.dto.teammember.TeamMemberDto;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.mapper.teammember.TeamMemberMapper;
import ru.kosterror.sportteamapi.model.SportTeam;
import ru.kosterror.sportteamapi.model.TeamMember;
import ru.kosterror.sportteamapi.model.TeamMemberRole;
import ru.kosterror.sportteamapi.repository.SportTeamRepository;
import ru.kosterror.sportteamapi.repository.TeamMemberRepository;
import ru.kosterror.sportteamapi.repository.TeamMemberRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, реализующий {@link TeamMemberService}. Используется для взаимодействия с {@link TeamMember}.
 */
@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final SportTeamRepository sportTeamRepository;
    private final TeamMemberRoleRepository teamMemberRoleRepository;
    private final TeamMemberMapper teamMemberMapper;

    @Override
    public List<TeamMemberDto> getTeamMembers(List<Long> sportTeamIds, List<Long> teamMemberRoleIds) {
        List<TeamMember> teamMembers;

        if (sportTeamIds != null && teamMemberRoleIds != null) {
            teamMembers = teamMemberRepository
                    .findTeamMembersBySportTeamIdInAndRoleIdIn(sportTeamIds, teamMemberRoleIds);
        } else if (sportTeamIds != null) {
            teamMembers = teamMemberRepository.findTeamMembersBySportTeamIdIn(sportTeamIds);
        } else if (teamMemberRoleIds != null) {
            teamMembers = teamMemberRepository.findTeamMembersByRoleIdIn(teamMemberRoleIds);
        } else {
            teamMembers = teamMemberRepository.findAll();
        }

        return teamMembers
                .stream()
                .map(teamMemberMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamMemberDto getTeamMember(Long id) throws NotFoundException {
        TeamMember teamMember = teamMemberRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Участник с id = '" + id + "' не найден"));

        return teamMemberMapper.entityToDto(teamMember);
    }

    @Override
    public List<BasicTeamMemberDto> getTeamMembersByTeam(Long sportTeamId,
                                                         Long teamMemberRoleId
    ) throws NotFoundException {
        if (!sportTeamRepository.existsById(sportTeamId)) {
            throw new NotFoundException("Команда с id = '" + sportTeamId + "' не найдена");
        }

        List<TeamMember> members;

        if (teamMemberRoleId == null) {
            members = teamMemberRepository.findBySportTeamId(sportTeamId);
        } else {
            members = teamMemberRepository.findBySportTeamIdAndRoleId(sportTeamId, teamMemberRoleId);
        }

        return members
                .stream()
                .map(teamMemberMapper::entityToBasicDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamMemberDto createTeamMember(
            CreateUpdateTeamMemberDto createUpdateTeamMemberDto
    ) throws NotFoundException {
        TeamMemberRole memberRole = teamMemberRoleRepository
                .findById(createUpdateTeamMemberDto.getRoleId())
                .orElseThrow(() -> new NotFoundException("Роль по заданному идентификатору не найдена"));

        SportTeam sportTeam = sportTeamRepository
                .findById(createUpdateTeamMemberDto.getTeamId())
                .orElseThrow(() -> new NotFoundException("Команда по заданному идентификатору не найдена"));

        TeamMember teamMember = teamMemberMapper.createDtoToEntity(createUpdateTeamMemberDto, memberRole, sportTeam);
        teamMember = teamMemberRepository.save(teamMember);

        return teamMemberMapper.entityToDto(teamMember);
    }

    @Override
    public TeamMemberDto updateTeamMember(Long id,
                                          CreateUpdateTeamMemberDto createUpdateTeamMemberDto
    ) throws NotFoundException {
        TeamMember teamMember = teamMemberRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Участник с id = '" + id + "' не найден."));

        SportTeam sportTeam = sportTeamRepository
                .findById(createUpdateTeamMemberDto.getTeamId())
                .orElseThrow(() -> new NotFoundException("Команда с id = '" + createUpdateTeamMemberDto.getTeamId() +
                        "' не найдена"));

        TeamMemberRole memberRole = teamMemberRoleRepository
                .findById(createUpdateTeamMemberDto.getRoleId())
                .orElseThrow(() -> new NotFoundException("Роль с id = '" + createUpdateTeamMemberDto.getTeamId() +
                        "' не найдена"));

        teamMember.setSportTeam(sportTeam);
        teamMember.setName(createUpdateTeamMemberDto.getName());
        teamMember.setSurname(createUpdateTeamMemberDto.getSurname());
        teamMember.setPatronymic(createUpdateTeamMemberDto.getPatronymic());
        teamMember.setBirthDate(createUpdateTeamMemberDto.getBirthDate());
        teamMember.setRole(memberRole);

        teamMember = teamMemberRepository.save(teamMember);

        return teamMemberMapper.entityToDto(teamMember);
    }

    @Override
    public TeamMemberDto moveTeamMember(Long id, MoveMemberDto moveMemberDto) throws NotFoundException {
        TeamMember teamMember = teamMemberRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Участник с id = '" + id + "' не найден."));

        SportTeam sportTeam = sportTeamRepository
                .findById(moveMemberDto.getSportTeamId())
                .orElseThrow(() -> new NotFoundException("Команда с id = '" + moveMemberDto.getSportTeamId() +
                        "' не найдена"));

        teamMember.setSportTeam(sportTeam);
        teamMember = teamMemberRepository.save(teamMember);

        return teamMemberMapper.entityToDto(teamMember);
    }
}
