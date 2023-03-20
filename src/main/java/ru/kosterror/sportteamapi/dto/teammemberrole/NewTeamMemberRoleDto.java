package ru.kosterror.sportteamapi.dto.teammemberrole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kosterror.sportteamapi.model.TeamMemberRole;

/**
 * Класс, который используется для запроса на создание новой сущности {@link TeamMemberRole}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTeamMemberRoleDto {

    private String name;

}
