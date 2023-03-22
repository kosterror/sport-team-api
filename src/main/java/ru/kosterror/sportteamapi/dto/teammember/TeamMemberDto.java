package ru.kosterror.sportteamapi.dto.teammember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kosterror.sportteamapi.dto.sportteam.SportTeamDto;
import ru.kosterror.sportteamapi.model.TeamMember;

/**
 * Класс используется для ответа на запросы и хранит в себе полную информацию {@link TeamMember}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMemberDto {

    BasicTeamMemberDto teamMember;

    SportTeamDto team;

}
