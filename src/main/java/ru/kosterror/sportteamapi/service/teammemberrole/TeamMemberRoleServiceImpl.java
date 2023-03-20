package ru.kosterror.sportteamapi.service.teammemberrole;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kosterror.sportteamapi.dto.teammemberrole.NewTeamMemberRoleDto;
import ru.kosterror.sportteamapi.dto.teammemberrole.TeamMemberRoleDto;
import ru.kosterror.sportteamapi.dto.teammemberrole.UpdateTeamMemberRoleDto;
import ru.kosterror.sportteamapi.exception.ConflictException;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.mapper.teammemberrole.TeamMemberRoleMapper;
import ru.kosterror.sportteamapi.model.TeamMemberRole;
import ru.kosterror.sportteamapi.repository.TeamMemberRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamMemberRoleServiceImpl implements TeamMemberRoleService {

    private final TeamMemberRoleRepository teamMemberRoleRepository;
    @Override
    public List<TeamMemberRoleDto> getTeamMemberRoles() {
        List<TeamMemberRole> teamMemberRoles = teamMemberRoleRepository.findAll();

        return teamMemberRoles
                .stream()
                .map(teamMemberRoleMapper::entityToDto)
                .collect(Collectors.toList());
    }

    private final TeamMemberRoleMapper teamMemberRoleMapper;

    @Override
    public TeamMemberRoleDto getTeamMemberRoleById(Long id) throws NotFoundException {
        TeamMemberRole teamMemberRole = teamMemberRoleRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Роль с id = '" + id + "' не найдена"));

        return teamMemberRoleMapper.entityToDto(teamMemberRole);
    }

    @Override
    public TeamMemberRoleDto createTeamMemberRole(NewTeamMemberRoleDto newTeamMemberRoleDto
    ) throws ConflictException {
        if (teamMemberRoleRepository.existsByName(newTeamMemberRoleDto.getName())) {
            throw new ConflictException("Роль с названием ='" +
                    newTeamMemberRoleDto.getName() + "' уже существует");
        }

        TeamMemberRole teamMemberRole = teamMemberRoleMapper.newTeamMemberRoleToEntity(newTeamMemberRoleDto);
        teamMemberRole = teamMemberRoleRepository.save(teamMemberRole);

        return teamMemberRoleMapper.entityToDto(teamMemberRole);
    }

    @Override
    public TeamMemberRoleDto updateTeamMemberRole(Long id,
                                                  UpdateTeamMemberRoleDto updateTeamMemberRoleDto
    ) throws NotFoundException, ConflictException {
        TeamMemberRole teamMemberRole = teamMemberRoleRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Роль с переданным id ='" + id + "' отсутствует"));

        if (teamMemberRoleRepository.existsByName(updateTeamMemberRoleDto.getName())) {
            throw new ConflictException("Роль с названием ='"
                    + updateTeamMemberRoleDto.getName() + "' уже существует");
        }

        teamMemberRole.setName(updateTeamMemberRoleDto.getName());
        teamMemberRole = teamMemberRoleRepository.save(teamMemberRole);

        return teamMemberRoleMapper.entityToDto(teamMemberRole);
    }

    @Override
    public void deleteTeamMemberRole(Long id) throws NotFoundException {
        if (!teamMemberRoleRepository.existsById(id)){
            throw new NotFoundException("Роль с id = '" + id + "' не найдена");
        }

        teamMemberRoleRepository.deleteById(id);
    }
}
