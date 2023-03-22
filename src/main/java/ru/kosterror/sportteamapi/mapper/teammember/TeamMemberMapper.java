package ru.kosterror.sportteamapi.mapper.teammember;

import ru.kosterror.sportteamapi.dto.teammember.BasicTeamMemberDto;
import ru.kosterror.sportteamapi.dto.teammember.TeamMemberDto;
import ru.kosterror.sportteamapi.model.TeamMember;

/**
 * Интерфейс, который предоставляет методы для конвертации {@link TeamMember} в связанные с ним классы и получения
 * его из них.
 */
public interface TeamMemberMapper {

    /**
     * Метод для конвертации {@link TeamMember} в {@link TeamMemberDto}.
     *
     * @param teamMember объект, который нужно конвертировать.
     * @return конвертированный объект.
     */
    TeamMemberDto entityToDto(TeamMember teamMember);

    /**
     * Метод для конвертации {@link TeamMember} в {@link BasicTeamMemberDto}.
     *
     * @param teamMember объект, который нужно конвертировать.
     * @return конвертированный объект.
     */
    BasicTeamMemberDto entityToBasicDto(TeamMember teamMember);

}
