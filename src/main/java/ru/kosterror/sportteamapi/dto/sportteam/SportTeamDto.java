package ru.kosterror.sportteamapi.dto.sportteam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kosterror.sportteamapi.dto.sporttype.SportTypeDto;

import java.time.LocalDate;

/**
 * Класс, который хранит в себе информацию о спортивной команде,
 * для ответов на запросы.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportTeamDto {

    private Long id;

    private String name;

    private SportTypeDto sportTypeDto;

    private LocalDate foundDate;

}
