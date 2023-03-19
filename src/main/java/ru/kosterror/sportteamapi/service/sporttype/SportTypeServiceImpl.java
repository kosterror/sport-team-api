package ru.kosterror.sportteamapi.service.sporttype;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kosterror.sportteamapi.dto.sporttype.NewSportTypeDto;
import ru.kosterror.sportteamapi.dto.sporttype.SportTypeDto;
import ru.kosterror.sportteamapi.exception.ConflictException;
import ru.kosterror.sportteamapi.mapper.sporttype.SportTypeMapper;
import ru.kosterror.sportteamapi.model.SportType;
import ru.kosterror.sportteamapi.repository.SportTypeRepository;

/**
 * Класс, реализующий {@link SportTypeService}. Используется для операций {@code CRUD} с {@link SportType}.
 */
@Service
@RequiredArgsConstructor
public class SportTypeServiceImpl implements SportTypeService {

    private final SportTypeRepository sportTypeRepository;
    private final SportTypeMapper sportTypeMapper;

    @Override
    public SportTypeDto createSportType(NewSportTypeDto newSportTypeDto) throws ConflictException {
        if (sportTypeRepository.existsByName(newSportTypeDto.getSportName())) {
            throw new ConflictException("Вид спорта с таким названием уже существует");
        }

        SportType sportType = sportTypeMapper.newSportTypeToEntity(newSportTypeDto);
        sportType = sportTypeRepository.save(sportType);

        return sportTypeMapper.entityToDto(sportType);
    }

}
