package ru.kosterror.sportteamapi.service.sporttype;

import ru.kosterror.sportteamapi.dto.sporttype.NewSportTypeDto;
import ru.kosterror.sportteamapi.dto.sporttype.SportTypeDto;
import ru.kosterror.sportteamapi.exception.ConflictException;
import ru.kosterror.sportteamapi.model.SportType;

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

}
