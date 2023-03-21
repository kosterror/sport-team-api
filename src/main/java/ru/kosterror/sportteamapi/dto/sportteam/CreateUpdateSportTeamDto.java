package ru.kosterror.sportteamapi.dto.sportteam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Класс для тел запросов на создание и обновление спортивной команды.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateSportTeamDto {

    private String name;

    private Long sportTypeId;

    private Date foundDate;

}
