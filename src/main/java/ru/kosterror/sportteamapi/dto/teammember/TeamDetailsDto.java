package ru.kosterror.sportteamapi.dto.teammember;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kosterror.sportteamapi.dto.sportteam.SportTeamDto;
import ru.kosterror.sportteamapi.dto.teammemberrole.TeamMemberRoleDto;
import ru.kosterror.sportteamapi.model.TeamMember;

/**
 * Класс для хранения информации о команде {@link TeamMember}.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDetailsDto {

    private SportTeamDto team;

    private TeamMemberRoleDto role;

}
