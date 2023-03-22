package ru.kosterror.sportteamapi.dto.teammember;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс для запроса для перевода участника в другую команду.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoveMemberDto {

    private Long sportTeamId;

}
