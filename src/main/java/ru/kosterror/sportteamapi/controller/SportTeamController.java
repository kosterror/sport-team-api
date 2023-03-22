
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
import ru.kosterror.sportteamapi.dto.sportteam.CreateUpdateSportTeamDto;
import ru.kosterror.sportteamapi.dto.sportteam.SportTeamDto;
import ru.kosterror.sportteamapi.exception.BadRequestException;
import ru.kosterror.sportteamapi.exception.ConflictException;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.model.SportTeam;
import ru.kosterror.sportteamapi.service.sportteam.SportTeamService;

import java.time.LocalDate;
import java.util.List;

/**
 * Контроллер для взаимодействия с {@link SportTeam}.
 */
@Tag(name = "Работа со спортивными командами")
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
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                            @RequestParam(required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finishDate
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
    @Operation(summary = "Получить спортивную команду по идентификатору.")
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

    /**
     * Метод для создания и сохранения новой спортивной команды.
     *
     * @param createUpdateSportTeamDto данные для создания спортивной команды.
     * @return информация о созданной спортивной команде.
     * @throws NotFoundException возникает, если вид спорта по указанному идентификатору не найден.
     * @throws ConflictException возникает, если название спортивной команды занято.
     */
    @Operation(summary = "Добавить команду.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Команда успешно создана."),
                    @ApiResponse(responseCode = "404", description = "Указанный вид спорта не найден.",
                            content = @Content(schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(responseCode = "409", description = "Название спортивной команды занято.",
                            content = @Content(schema = @Schema(implementation = ApiError.class)))
            }
    )
    @PostMapping
    public SportTeamDto createSportTeam(
            @RequestBody CreateUpdateSportTeamDto createUpdateSportTeamDto
    ) throws ConflictException, NotFoundException {
        return service.createSportTeam(createUpdateSportTeamDto);
    }

    /**
     * Метод для обновления данных спортивной команды.
     *
     * @param id                       идентификатор обновляемой команды.
     * @param createUpdateSportTeamDto новая информация о спортивной команде.
     * @return сохраненная информация о спортивной команде.
     * @throws NotFoundException возникает, если какая-то указанная информация не найден.
     * @throws ConflictException возникает, если какая-то указанная информация уже занята.
     */
    @Operation(summary = "Изменить данные команды.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Команда успешно изменена."),
                    @ApiResponse(responseCode = "404", description = "Данные по указанной информации не найдены.",
                            content = @Content(schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(responseCode = "409", description = "Какие-либо данные заняты другой командой.",
                            content = @Content(schema = @Schema(implementation = ApiError.class)))
            }
    )
    @PutMapping("/{id}")
    public SportTeamDto updateSportTeam(@PathVariable Long id,
                                        @RequestBody CreateUpdateSportTeamDto createUpdateSportTeamDto
    ) throws ConflictException, NotFoundException {
        return service.updateSportTeam(id, createUpdateSportTeamDto);
    }

    /**
     * Метод для удаления спортивной команды.
     *
     * @param id идентификатор спортивной команды.
     * @throws NotFoundException возникает, если спортивная команда не найдена.
     */
    @Operation(summary = "Удалить команду.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Команда успешно изменена."),
                    @ApiResponse(responseCode = "404", description = "Команда не найдена.",
                            content = @Content(schema = @Schema(implementation = ApiError.class)))
            }
    )
    @DeleteMapping("{id}")
    public void deleteSportTeam(@PathVariable Long id) throws NotFoundException {
        service.deleteSportTeam(id);
    }

}
