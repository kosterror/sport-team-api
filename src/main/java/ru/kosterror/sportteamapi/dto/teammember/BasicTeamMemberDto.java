package ru.kosterror.sportteamapi.dto.teammember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kosterror.sportteamapi.model.TeamMember;

import java.time.LocalDate;

/**
 * Класс с основной информацией о {@link TeamMember}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicTeamMemberDto {

    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    private LocalDate birthDate;

}
