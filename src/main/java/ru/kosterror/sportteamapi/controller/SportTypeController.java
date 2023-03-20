package ru.kosterror.sportteamapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kosterror.sportteamapi.dto.sporttype.ApiError;
import ru.kosterror.sportteamapi.dto.sporttype.NewSportTypeDto;
import ru.kosterror.sportteamapi.dto.sporttype.SportTypeDto;
import ru.kosterror.sportteamapi.dto.sporttype.UpdateSportTypeDto;
import ru.kosterror.sportteamapi.exception.ConflictException;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.model.SportType;
import ru.kosterror.sportteamapi.service.sporttype.SportTypeService;

import java.util.List;

/**
 * Контроллер для {@code CRUD} операций над {@link SportType}
 */
@RestController
@RequestMapping("/api/sport-types")
@RequiredArgsConstructor
@Tag(name = "Работа с видами спорта")
public class SportTypeController {

    private final SportTypeService service;

    /**
     * Метод для получения всех видов спорта.
     *
     * @return список видов спорта.
     */
    @GetMapping
    @Operation(summary = "Получить список видов спорта.")
    @ApiResponse(responseCode = "200", description = "Данные получены успешно.")
    public List<SportTypeDto> getSportTypes() {
        return service.getSportTypes();
    }

    /**
     * Метод для получения вида спорта по его идентификатору.
     *
     * @param id идентификатор.
     * @return информация о виде спорта.
     * @throws NotFoundException возникает, если вид спорта по заданному идентификатору не найден.
     */
    @Operation(summary = "Получить вид спорта по идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные получены успешно."),
            @ApiResponse(responseCode = "404",
                    description = "Вид спорта по заданному идентификатору не найден.",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @GetMapping("/{id}")
    public SportTypeDto getSportType(@PathVariable Long id) throws NotFoundException {
        return service.getSportTypeById(id);
    }

    /**
     * Метод для создания нового вида спорта.
     *
     * @param newSportTypeDto dto с данными для нового вида спорта.
     * @return dto с данными о созданном виде спорта.
     * @throws ConflictException исключение, которое может возникнуть,
     *                           если вид спорта с таким названием уже существует.
     */
    @Operation(summary = "Создать вид спорта.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Вид спорта создан успешно."),
            @ApiResponse(responseCode = "409",
                    description = "Конфликт.",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PostMapping
    public SportTypeDto createSportType(@RequestBody NewSportTypeDto newSportTypeDto) throws ConflictException {
        return service.createSportType(newSportTypeDto);
    }

    /**
     * Метод для обновления информации о виде спорта.
     *
     * @param id                 идентификатор обновляемого вида спорта.
     * @param updateSportTypeDto новые данные для вида спорта.
     * @return сохраненные данные о виде спорта.
     * @throws ConflictException исключение, которое может возникнуть, если вид спорта с
     *                           таким названием уже существует.
     * @throws NotFoundException исключение, которое может возникнуть, если вид спорта с заданным
     *                           идентификатором не найден.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Изменить вид спорта.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Вид спорта изменен успешно."),
            @ApiResponse(responseCode = "404",
                    description = "Вид спорта не найден.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409",
                    description = "Вид спорта с таким названием уже существует.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    public SportTypeDto updateSportType(@PathVariable Long id,
                                        @RequestBody UpdateSportTypeDto updateSportTypeDto
    ) throws ConflictException, NotFoundException {
        return service.updateSportType(id, updateSportTypeDto);
    }

    /**
     * Метод для удаления вида спорта и команд, которые относятся к этому виду спорта.
     *
     * @param id идентификатор удаляемого вида спорта.
     * @throws NotFoundException исключение, которое может возникнуть, если вид спорта
     *                           с заданным идентификатором не найден.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить вид спорта.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Вид спорта успешно удален."),
            @ApiResponse(responseCode = "404",
                    description = "Вид спорта не найден.",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public void deleteSportType(@PathVariable Long id) throws NotFoundException {
        service.deleteSportType(id);
    }

}
