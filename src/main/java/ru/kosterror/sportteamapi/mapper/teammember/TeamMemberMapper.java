package ru.kosterror.sportteamapi.mapper.teammember;

import ru.kosterror.sportteamapi.dto.teammember.BasicTeamMemberDto;
import ru.kosterror.sportteamapi.dto.teammember.CreateUpdateTeamMemberDto;
import ru.kosterror.sportteamapi.dto.teammember.TeamMemberDto;
import ru.kosterror.sportteamapi.model.SportTeam;
import ru.kosterror.sportteamapi.model.TeamMember;
import ru.kosterror.sportteamapi.model.TeamMemberRole;

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

    /**
     * Метод для создания объекта {@link TeamMember}. <strong>У нового объекта {@code id} будет равен
     * {@code null}</strong>.
     *
     * @param createUpdateTeamMemberDto основная информация.
     * @param memberRole                роль.
     * @param sportTeam                 спортивная команда.
     * @return новый объект {@link  TeamMember}.
     */
    TeamMember createDtoToEntity(CreateUpdateTeamMemberDto createUpdateTeamMemberDto,
                                 TeamMemberRole memberRole,
                                 SportTeam sportTeam
    );

}
