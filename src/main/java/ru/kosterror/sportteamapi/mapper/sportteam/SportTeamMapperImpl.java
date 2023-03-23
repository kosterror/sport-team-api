package ru.kosterror.sportteamapi.mapper.sportteam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kosterror.sportteamapi.dto.sportteam.CreateUpdateSportTeamDto;
import ru.kosterror.sportteamapi.dto.sportteam.SportTeamDto;
import ru.kosterror.sportteamapi.mapper.sporttype.SportTypeMapper;
import ru.kosterror.sportteamapi.model.SportTeam;
import ru.kosterror.sportteamapi.model.SportType;

/**
 * Класс, реализующий интерфейс {@link SportTeamMapper}. Используется для конвертации
 * {@link SportTeam} в связанные с ним классы и для получения его из них.
 */
@Component
@RequiredArgsConstructor
public class SportTeamMapperImpl implements SportTeamMapper {

    private final SportTypeMapper sportTypeMapper;

    @Override
    public SportTeam newSportTeamToEntity(CreateUpdateSportTeamDto createUpdateSportTeamDto,
                                          SportType sportType) {
        return SportTeam.builder()
                .name(createUpdateSportTeamDto.getName())
                .sportType(sportType)
                .foundDate(createUpdateSportTeamDto.getFoundDate())
                .build();
    }

    @Override
    public SportTeamDto entityToDto(SportTeam sportTeam) {
        SportTeamDto sportTeamDto = SportTeamDto.builder()
                .id(sportTeam.getId())
                .name(sportTeam.getName())
                .foundDate(sportTeam.getFoundDate())
                .build();

        if (sportTeam.getSportType() != null) {
            sportTeamDto.setSportTypeDto(sportTypeMapper.entityToDto(sportTeam.getSportType()));
        }

        return sportTeamDto;
    }

}
