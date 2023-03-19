package ru.kosterror.sportteamapi.mapper.sporttype;

import org.springframework.stereotype.Component;
import ru.kosterror.sportteamapi.dto.sporttype.NewSportTypeDto;
import ru.kosterror.sportteamapi.dto.sporttype.SportTypeDto;
import ru.kosterror.sportteamapi.model.SportType;

/**
 * Класс, реализующий интерфейс {@link SportTypeMapper}. Используется для конвертации
 * {@link SportType} в связанные с ним классы и для получения его из них.
 */
@Component
public class SportTypeMapperImpl implements SportTypeMapper {

    @Override
    public SportType newSportTypeToEntity(NewSportTypeDto newSportTypeDto) {
        return SportType
                .builder()
                .name(newSportTypeDto.getSportName())
                .build();
    }

    @Override
    public SportTypeDto entityToDto(SportType sportType) {
        return SportTypeDto
                .builder()
                .id(sportType.getId())
                .name(sportType.getName())
                .build();
    }

}
