package ru.kosterror.sportteamapi.mapper.teammemberrole;

import ru.kosterror.sportteamapi.dto.teammemberrole.NewTeamMemberRoleDto;
import ru.kosterror.sportteamapi.dto.teammemberrole.TeamMemberRoleDto;
import ru.kosterror.sportteamapi.model.TeamMemberRole;

/**
 * Класс реализующий интерфейс {@link TeamMemberRoleMapper}. Используется для конвертации {@link TeamMemberRole}
 * в связанные с ним классы и для получения его из них.
 */
public class TeamMemberRoleMapperImpl implements TeamMemberRoleMapper {

    @Override
    public TeamMemberRole newTeamMemberRoleToEntity(NewTeamMemberRoleDto newTeamMemberRoleDto) {
        return TeamMemberRole
                .builder()
                .name(newTeamMemberRoleDto.getName())
                .build();
    }

    @Override
    public TeamMemberRoleDto entityToDto(TeamMemberRole teamMemberRole) {
        return TeamMemberRoleDto
                .builder()
                .id(teamMemberRole.getId())
                .name(teamMemberRole.getName())
                .build();
    }

}
