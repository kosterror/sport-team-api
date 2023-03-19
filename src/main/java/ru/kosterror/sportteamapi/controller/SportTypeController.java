package ru.kosterror.sportteamapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kosterror.sportteamapi.dto.sporttype.NewSportTypeDto;
import ru.kosterror.sportteamapi.dto.sporttype.SportTypeDto;
import ru.kosterror.sportteamapi.exception.ConflictException;
import ru.kosterror.sportteamapi.model.SportType;
import ru.kosterror.sportteamapi.service.sporttype.SportTypeService;

/**
 * Контроллер для {@code CRUD} операций над {@link SportType}
 */
@RestController
@RequestMapping("/api/sport-types")
@RequiredArgsConstructor
public class SportTypeController {

    private final SportTypeService service;

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

}
