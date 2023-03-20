package ru.kosterror.sportteamapi.dto.memberrole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kosterror.sportteamapi.model.TeamMemberRole;

/**
 * Класс, который используется для запроса на изменение сущности {@link TeamMemberRole}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeamMemberRoleDto {

    private String name;

}
