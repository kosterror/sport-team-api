
package ru.kosterror.sportteamapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.kosterror.sportteamapi.dto.ApiError;
import ru.kosterror.sportteamapi.dto.sportteam.SportTeamDto;
import ru.kosterror.sportteamapi.exception.BadRequestException;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.model.SportTeam;
import ru.kosterror.sportteamapi.service.sportteam.SportTeamService;

import java.util.Date;
import java.util.List;

/**
 * Контроллер для взаимодействия с {@link SportTeam}.2024-01-01
 */
@Tag(name = "Работа со спортивными командами.")
@RestController
@RequestMapping("/api/sport-teams")
@RequiredArgsConstructor
public class SportTeamController {

    private final SportTeamService service;

    /**
     * Метод для получения списка команд. Все параметры могут быть {@code null}. <strong>Но {@code startDate} и
     * {@code finishDate} должны быть одновременно {@code null} или не {@code null}.
     * Причем, {@code startDate < finishDate} </strong>.
     *
     * @param sportTypeIds список идентификаторов видов спорта. Может быть {@code null}.
     * @param startDate    начальная дата периода для поиска команд по дате основания.
     * @param finishDate   конечная дата периода для поиска команд по дате основания.
     * @return список найденных команд, удовлетворяющих условиям.
     * @throws BadRequestException возникает, если входные данные некорректны.
     */
    @Operation(summary = "Получить список команд.", description = "Все параметры могут быть null. Но startDate и " +
            "finishDate должны быть одновременно null или не null. Причем startDate < finishDate. " +
            "Формат дат: yyyy-MM-dd.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены успешно."),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные.",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @GetMapping
    public List<SportTeamDto> getSportTeams(@RequestParam(required = false) List<Long> sportTypeIds,
                                            @RequestParam(required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                            @RequestParam(required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date finishDate
    ) throws BadRequestException {
        return service.getSportTeams(sportTypeIds, startDate, finishDate);
    }

    /**
     * Метод для получения информации о команде по её идентификатору.
     *
     * @param id идентификатор команды.
     * @return информацию о команде.
     * @throws NotFoundException возникает, если команда по идентификатору не найдена.
     */
    @Operation(summary = "Получить вид спорта по идентификатору.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Данные были получены успешно."),
                    @ApiResponse(responseCode = "404", description = "Команда не найдена.",
                            content = @Content(schema = @Schema(implementation = ApiError.class)))
            }
    )
    @GetMapping("/{id}")
    public SportTeamDto getSportTeamById(@PathVariable Long id) throws NotFoundException {
        return service.getSportTeamById(id);
    }

}
