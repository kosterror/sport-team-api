package ru.kosterror.sportteamapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
public class SportTypeController {

    private final SportTypeService service;

    /**
     * Метод для получения всех видов спорта.
     *
     * @return список видов спорта.
     */
    @GetMapping
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
    public void deleteSportType(@PathVariable Long id) throws NotFoundException {
        service.deleteSportType(id);
    }

}
