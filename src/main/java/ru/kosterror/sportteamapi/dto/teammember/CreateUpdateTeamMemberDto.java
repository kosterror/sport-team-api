package ru.kosterror.sportteamapi.dto.teammember;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kosterror.sportteamapi.model.TeamMember;

import java.time.LocalDate;

/**
 * Класс используется для тел запросов на создание и обновление {@link TeamMember}.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateTeamMemberDto {

    private String name;

    private String surname;

    private String patronymic;

    private LocalDate birthDate;

    private Long teamId;

    private Long roleId;

}
