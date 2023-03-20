package ru.kosterror.sportteamapi.service.teammemberrole;

import ru.kosterror.sportteamapi.dto.teammemberrole.NewTeamMemberRoleDto;
import ru.kosterror.sportteamapi.dto.teammemberrole.TeamMemberRoleDto;
import ru.kosterror.sportteamapi.dto.teammemberrole.UpdateTeamMemberRoleDto;
import ru.kosterror.sportteamapi.exception.ConflictException;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.model.TeamMemberRole;

import java.util.List;

/**
 * Интерфейс, который предоставляет методы для операций {@code CRUD} с {@link TeamMemberRole}.
 */
public interface TeamMemberRoleService {

    /**
     * Метод для создания новой роли.
     *
     * @param newTeamMemberRoleDto данные для новой роли.
     * @return информация о созданной роли.
     * @throws ConflictException возникает, если название роли занято.
     */
    TeamMemberRoleDto createTeamMemberRole(NewTeamMemberRoleDto newTeamMemberRoleDto) throws ConflictException;

    /**
     * Метод для изменений роли.
     *
     * @param id                      идентификатор роли.
     * @param updateTeamMemberRoleDto информация для изменения роли.
     * @return информация об обновленной роли.
     * @throws NotFoundException возникает, если роль с переданным идентификатором не найдена.
     * @throws ConflictException возникает, если название роли занято.
     */
    TeamMemberRoleDto updateTeamMemberRole(Long id,
                                           UpdateTeamMemberRoleDto updateTeamMemberRoleDto
    ) throws NotFoundException, ConflictException;

    /**
     * Метод для удаления роли.
     *
     * @param id идентификатор роли.
     * @throws NotFoundException возникает, если роль с переданным идентификатором не найдена.
     */
    void deleteTeamMemberRole(Long id) throws NotFoundException;

    /**
     * Метод для получения всех ролей.
     *
     * @return список всех ролей.
     */
    List<TeamMemberRoleDto> getTeamMemberRoles();

    /**
     * Метод для получения информации о конкретной роли.
     *
     * @param id идентификатор роли.
     * @return информация о найденной роли.
     * @throws NotFoundException возникает, если роль с переданным идентификатором не найдена.
     */
    TeamMemberRoleDto getTeamMemberRoleById(Long id) throws NotFoundException;

}
