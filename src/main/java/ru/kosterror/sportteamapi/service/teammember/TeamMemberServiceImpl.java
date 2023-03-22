package ru.kosterror.sportteamapi.service.teammember;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kosterror.sportteamapi.dto.teammember.TeamMemberDto;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.mapper.teammember.TeamMemberMapper;
import ru.kosterror.sportteamapi.model.TeamMember;
import ru.kosterror.sportteamapi.repository.TeamMemberRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, реализующий {@link TeamMemberService}. Используется для взаимодействия с {@link TeamMember}.
 */
@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
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

}
