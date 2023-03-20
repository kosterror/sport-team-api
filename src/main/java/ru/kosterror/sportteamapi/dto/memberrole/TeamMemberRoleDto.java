package ru.kosterror.sportteamapi.dto.memberrole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kosterror.sportteamapi.model.TeamMemberRole;

/**
 * Класс, который используется для ответа на запросы и хранит в себе информация {@link TeamMemberRole}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMemberRoleDto {

    private Long id;

    private String name;

}
