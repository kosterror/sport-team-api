package ru.kosterror.sportteamapi.mapper.sporttype;

import ru.kosterror.sportteamapi.dto.sporttype.NewSportTypeDto;
import ru.kosterror.sportteamapi.dto.sporttype.SportTypeDto;
import ru.kosterror.sportteamapi.model.SportType;

/**
 * Интерфейс, который предоставляет методы для конвертации
 * {@link SportType} в связанные с ним классы и для получения его из них.
 */
public interface SportTypeMapper {

    /**
     * Метод для создания объекта {@link SportType} на основе {@link NewSportTypeDto}.
     * <strong>Атрибут {@code id} у нового объекта будет равен {@code null}</strong>.
     *
     * @param newSportTypeDto объект, на основе которого создастся {@link SportType}.
     * @return новый объект {@link SportType}, полученный на основе {@code newSportTypeDto}.
     */
    SportType newSportTypeToEntity(NewSportTypeDto newSportTypeDto);

    /**
     * Метод для создания объекта {@link SportTypeDto} на основе {@link SportType}.
     *
     * @param sportType объект, на основе которого создастся {@link SportTypeDto}.
     * @return новый объект {@link SportTypeDto}, полученный на основе {@code sportType}.
     */
    SportTypeDto entityToDto(SportType sportType);

}
