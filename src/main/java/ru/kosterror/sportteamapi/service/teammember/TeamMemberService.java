package ru.kosterror.sportteamapi.service.teammember;

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
     * @param sportTeamIds список идентификаторов доступных команд.
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

}
