package ru.kosterror.sportteamapi.service.sporttype;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kosterror.sportteamapi.dto.sporttype.NewSportTypeDto;
import ru.kosterror.sportteamapi.dto.sporttype.SportTypeDto;
import ru.kosterror.sportteamapi.dto.sporttype.UpdateSportTypeDto;
import ru.kosterror.sportteamapi.exception.ConflictException;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.mapper.sporttype.SportTypeMapper;
import ru.kosterror.sportteamapi.model.SportType;
import ru.kosterror.sportteamapi.repository.SportTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, реализующий {@link SportTypeService}. Используется для операций {@code CRUD} с {@link SportType}.
 */
@Service
@RequiredArgsConstructor
public class SportTypeServiceImpl implements SportTypeService {

    private final SportTypeRepository sportTypeRepository;
    private final SportTypeMapper sportTypeMapper;

    @Override
    public List<SportTypeDto> getSportTypes() {
        List<SportType> sportTypes = sportTypeRepository.findAll();

        return sportTypes
                .stream()
                .map(sportTypeMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SportTypeDto getSportTypeById(Long id) throws NotFoundException {
        SportType sportType = sportTypeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Вид спорта с id = '" + id + "' не найден"));

        return sportTypeMapper.entityToDto(sportType);
    }

    @Override
    public SportTypeDto createSportType(NewSportTypeDto newSportTypeDto) throws ConflictException {
        if (sportTypeRepository.existsByName(newSportTypeDto.getSportName())) {
            throw new ConflictException("Вид спорта с таким названием уже существует");
        }

        SportType sportType = sportTypeMapper.newSportTypeToEntity(newSportTypeDto);
        sportType = sportTypeRepository.save(sportType);

        return sportTypeMapper.entityToDto(sportType);
    }

    @Override
    public SportTypeDto updateSportType(Long id,
                                        UpdateSportTypeDto updateSportTypeDto
    ) throws ConflictException, NotFoundException {
        SportType sportType = sportTypeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Вид спорта с id = '" + id + "' не найден"));

        if (sportTypeRepository.existsByName(updateSportTypeDto.getSportName())) {
            throw new ConflictException("Вид спорта с таким названием уже существует");
        }

        sportType.setName(updateSportTypeDto.getSportName());
        sportType = sportTypeRepository.save(sportType);
        return sportTypeMapper.entityToDto(sportType);
    }

    @Override
    public void deleteSportType(Long id) throws NotFoundException {
        if (!sportTypeRepository.existsById(id)) {
            throw new NotFoundException("Вид спорта с id = '" + id + "' не найден");
        }

        sportTypeRepository.deleteById(id);
    }

}
