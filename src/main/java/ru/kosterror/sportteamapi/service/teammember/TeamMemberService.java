package ru.kosterror.sportteamapi.service.teammember;

import ru.kosterror.sportteamapi.dto.teammember.TeamMemberDto;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.model.TeamMember;

/**
 * Сервис для взаимодействия с {@link TeamMember}
 */
public interface TeamMemberService {

    /**
     * Метод для получения участника команды по идентификатору.
     *
     * @param id идентификатор участника.
     * @return информацией об участнике команды.
     * @throws NotFoundException возникает, если участник команды не найден.
     */
    TeamMemberDto getTeamMember(Long id) throws NotFoundException;

}
