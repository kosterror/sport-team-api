package ru.kosterror.sportteamapi.service.teammember;

import ru.kosterror.sportteamapi.dto.teammember.BasicTeamMemberDto;
import ru.kosterror.sportteamapi.dto.teammember.CreateUpdateTeamMemberDto;
import ru.kosterror.sportteamapi.dto.teammember.MoveMemberDto;
import ru.kosterror.sportteamapi.dto.teammember.TeamMemberDto;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.model.TeamMember;

import java.util.List;

/**
 * Сервис для взаимодействия с {@link TeamMember}
 */
public interface TeamMemberService {

    /**
     * Метод для получения участников с определенными параметрами.
     *
     * @param sportTeamIds      список идентификаторов доступных команд.
     * @param teamMemberRoleIds список идентификаторов доступных ролей.
     * @return список подходящий участников.
     */
    List<TeamMemberDto> getTeamMembers(List<Long> sportTeamIds, List<Long> teamMemberRoleIds);

    /**
     * Метод для получения участника команды по идентификатору.
     *
     * @param id идентификатор участника.
     * @return информацией об участнике команды.
     * @throws NotFoundException возникает, если участник команды не найден.
     */
    TeamMemberDto getTeamMember(Long id) throws NotFoundException;

    /**
     * Метод для получения участников команд, которые относятся к конкретной спортивной команде. Есть возможность
     * отфильтровать участников по роли.
     *
     * @param sportTeamId      идентификатор спортивной команды. Не может быть {@code null}.
     * @param teamMemberRoleId идентификатор роли, может быть {@code null}.
     * @return список подходящих участников.
     * @throws NotFoundException возникает, если спортивной команды или роли не существует.
     */
    List<BasicTeamMemberDto> getTeamMembersByTeam(Long sportTeamId, Long teamMemberRoleId) throws NotFoundException;

    /**
     * Метод для создания участника команды.
     *
     * @param createUpdateTeamMemberDto информация для создания участника.
     * @return сохраненной информация о созданном участнике команды.
     * @throws NotFoundException возникает, если по идентификатору команды и/или роли не удалось найти информации.
     */
    TeamMemberDto createTeamMember(CreateUpdateTeamMemberDto createUpdateTeamMemberDto) throws NotFoundException;

    /**
     * Метод для изменения данных участника команды.
     *
     * @param createUpdateTeamMemberDto новая информация об участнике.
     * @return новая сохраненная информация.
     * @throws NotFoundException возникает, если какая-либо информация по указанным идентификаторам не найдена.
     */
    TeamMemberDto updateTeamMember(Long id,
                                   CreateUpdateTeamMemberDto createUpdateTeamMemberDto
    ) throws NotFoundException;

    /**
     * Метод для перевода участника в другую спортивную команду.
     *
     * @param id            идентификатор участника.
     * @param moveMemberDto данные о новой команде.
     * @return обновленная информация об участнике команды.
     * @throws NotFoundException возникает, если не удалось найти участника и/или команду.
     */
    TeamMemberDto moveTeamMember(Long id, MoveMemberDto moveMemberDto) throws NotFoundException;
}
