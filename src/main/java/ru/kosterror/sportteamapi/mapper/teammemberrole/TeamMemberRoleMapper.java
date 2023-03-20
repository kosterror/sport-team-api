package ru.kosterror.sportteamapi.mapper.teammemberrole;

import ru.kosterror.sportteamapi.dto.teammemberrole.NewTeamMemberRoleDto;
import ru.kosterror.sportteamapi.dto.teammemberrole.TeamMemberRoleDto;
import ru.kosterror.sportteamapi.model.TeamMemberRole;

/**
 * Интерфейс, который предоставляет методы для конвертации {@link TeamMemberRole} в связанные
 * с ним классы и для получения его из них.
 */
public interface TeamMemberRoleMapper {

    /**
     * Метод для создания нового объекта {@link TeamMemberRole} на основе {@link NewTeamMemberRoleDto}.
     * <strong>Атрибут {@code id} у нового объекта будет равен {code null}</strong>.
     *
     * @param newTeamMemberRoleDto объект, на основе которого создастся новый объект.
     * @return новый объект {@link TeamMemberRole}, полученный на основе {@code newTeamMemberRoleDto}.
     */
    TeamMemberRole newTeamMemberRoleToEntity(NewTeamMemberRoleDto newTeamMemberRoleDto);

    /**
     * Метод для создания нового объекта {@link TeamMemberRoleDto} на основе {@link TeamMemberRole}.
     *
     * @param teamMemberRole объект, на основе которого создастся {@link TeamMemberRoleDto}.
     * @return новый объект {@link TeamMemberRoleDto}, полученный на основе {@code teamMemberRole}.
     */
    TeamMemberRoleDto entityToDto(TeamMemberRole teamMemberRole);

}
