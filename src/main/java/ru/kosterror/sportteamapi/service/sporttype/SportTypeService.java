package ru.kosterror.sportteamapi.service.sporttype;

import ru.kosterror.sportteamapi.dto.sporttype.NewSportTypeDto;
import ru.kosterror.sportteamapi.dto.sporttype.SportTypeDto;
import ru.kosterror.sportteamapi.dto.sporttype.UpdateSportTypeDto;
import ru.kosterror.sportteamapi.exception.ConflictException;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.model.SportType;

import java.util.List;

/**
 * Интерфейс, который предоставляет методы для операций {@code CRUD} с {@link SportType}.
 */
public interface SportTypeService {

    /**
     * Метод для создания нового вида спорта.
     *
     * @param newSportTypeDto dto с данными для нового вида спорта.
     * @return dto с информацией о созданном виде спорта.
     * @throws ConflictException возникает, если название вида спорта уже занято.
     */
    SportTypeDto createSportType(NewSportTypeDto newSportTypeDto) throws ConflictException;

    /**
     * Метод для обновления информации о виде спорта.
     *
     * @param id                 идентификатор вида спорта.
     * @param updateSportTypeDto новая информация о виде спорта.
     * @return обновленная информация о виде спорта.
     * @throws ConflictException возникает, если названия вида спорта уже занято.
     * @throws NotFoundException возникает, если вид спорта с заданным идентификатором не найден.
     */
    SportTypeDto updateSportType(Long id,
                                 UpdateSportTypeDto updateSportTypeDto
    ) throws ConflictException, NotFoundException;

    /**
     * Метод для удаления вида спорта и команд, которые к нему относятся.
     *
     * @param id идентификатор вида спорта.
     * @throws NotFoundException возникает, если вид спорта с заданным идентификатором не найден.
     */
    void deleteSportType(Long id) throws NotFoundException;

    /**
     * Метод для получения всех видов спорта.
     *
     * @return список с видами спорта.
     */
    List<SportTypeDto> getSportTypes();

    /**
     * Метод для получения вида спорта по идентификатору.
     *
     * @param id идентификатор.
     * @return информация о виде спорта.
     * @throws NotFoundException возникает, если вид спорта по переданному идентификатору не найден.
     */
    SportTypeDto getSportTypeById(Long id) throws NotFoundException;

}
