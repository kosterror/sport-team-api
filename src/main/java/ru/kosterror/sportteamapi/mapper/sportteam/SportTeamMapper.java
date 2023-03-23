package ru.kosterror.sportteamapi.mapper.sportteam;

import ru.kosterror.sportteamapi.dto.sportteam.CreateUpdateSportTeamDto;
import ru.kosterror.sportteamapi.dto.sportteam.SportTeamDto;
import ru.kosterror.sportteamapi.model.SportTeam;
import ru.kosterror.sportteamapi.model.SportType;

/**
 * Интерфейс, который предоставляет методы для конвертации {@link SportTeam}
 * в связанные с ним классы и для получения его из них.
 */
public interface SportTeamMapper {

    /**
     * Метод для создания объекта {@link SportTeam} на основе {@link CreateUpdateSportTeamDto} и {@link SportType}.
     * <strong>Атрибут {@code id} у нового объекта будет равен {@code null}</strong>
     *
     * @param createUpdateSportTeamDto основная информация для нового объекта.
     * @param sportType                информация о виде спорта для нового объекта.
     * @return новый объект {@link SportTeam}.
     */
    SportTeam newSportTeamToEntity(CreateUpdateSportTeamDto createUpdateSportTeamDto,
                                   SportType sportType);

    /**
     * Метод для создания объекта {@link SportTeamDto} на основе {@link SportTeam}.
     *
     * @param sportTeam информация для нового объекта.
     * @return новый объект {@link SportTeamDto}.
     */
    SportTeamDto entityToDto(SportTeam sportTeam);

}
