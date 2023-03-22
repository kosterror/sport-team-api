package ru.kosterror.sportteamapi.service.sportteam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kosterror.sportteamapi.dto.sportteam.CreateUpdateSportTeamDto;
import ru.kosterror.sportteamapi.dto.sportteam.SportTeamDto;
import ru.kosterror.sportteamapi.exception.BadRequestException;
import ru.kosterror.sportteamapi.exception.ConflictException;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.mapper.sportteam.SportTeamMapper;
import ru.kosterror.sportteamapi.model.SportTeam;
import ru.kosterror.sportteamapi.model.SportType;
import ru.kosterror.sportteamapi.repository.SportTeamRepository;
import ru.kosterror.sportteamapi.repository.SportTypeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Класс, реализующий интерфейс {@link SportTeamService}.
 */
@Service
@RequiredArgsConstructor
public class SportTeamServiceImpl implements SportTeamService {

    private final SportTeamRepository sportTeamRepository;
    private final SportTypeRepository sportTypeRepository;
    private final SportTeamMapper sportTeamMapper;

    @Override
    public SportTeamDto getSportTeamById(Long id) throws NotFoundException {
        SportTeam sportTeam = sportTeamRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Команда с id = '" + id + "' не найдена"));

        return sportTeamMapper.entityToDto(sportTeam);
    }

    @Override
    public SportTeamDto createSportTeam(
            CreateUpdateSportTeamDto createUpdateSportTeamDto
    ) throws NotFoundException, ConflictException {
        if (sportTeamRepository.existsByName(createUpdateSportTeamDto.getName())) {
            throw new ConflictException("Спортивная команда с таким именем занята");
        }

        SportType sportType = sportTypeRepository
                .findById(createUpdateSportTeamDto.getSportTypeId())
                .orElseThrow(() -> new NotFoundException("Вид спорта с id = '" +
                        createUpdateSportTeamDto.getSportTypeId() + "' не найден"));

        SportTeam sportTeam = sportTeamMapper.newSportTeamToEntity(createUpdateSportTeamDto, sportType);
        sportTeam = sportTeamRepository.save(sportTeam);

        return sportTeamMapper.entityToDto(sportTeam);
    }

    @Override
    public SportTeamDto updateSportTeam(Long id,
                                        CreateUpdateSportTeamDto createUpdateSportTeamDto
    ) throws NotFoundException, ConflictException {
        SportTeam sportTeam = sportTeamRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Спортивная команда с id = '" + id + "' не найдена"));

        SportType sportType = sportTypeRepository
                .findById(createUpdateSportTeamDto.getSportTypeId())
                .orElseThrow(() -> new NotFoundException("Вид спорта с id = '"
                        + createUpdateSportTeamDto.getSportTypeId() + "' не найден")
                );

        Optional<SportTeam> sportTeamWithSampleName = sportTeamRepository
                .findByName(createUpdateSportTeamDto.getName());

        if (sportTeamWithSampleName.isPresent() && !sportTeamWithSampleName.get().getId().equals(id)) {
            throw new ConflictException("Название спортивной команды = '" + createUpdateSportTeamDto.getName()
                    + "' уже занято.");
        }

        sportTeam.setName(createUpdateSportTeamDto.getName());
        sportTeam.setSportType(sportType);
        sportTeam.setFoundDate(createUpdateSportTeamDto.getFoundDate());
        sportTeam = sportTeamRepository.save(sportTeam);

        return sportTeamMapper.entityToDto(sportTeam);
    }

    @Override
    public List<SportTeamDto> getSportTeams(List<Long> sportTypeIds,
                                            LocalDate startDate,
                                            LocalDate finishDate
    ) throws BadRequestException {
        if (startDate != null && finishDate == null || startDate == null && finishDate != null) {
            throw new BadRequestException("Необходимо одновременно заполнить или не заполнить поля с датами");
        }

        if (startDate != null && startDate.isAfter(finishDate)) {
            throw new BadRequestException("Начальная дата должна быть меньше конечной даты");
        }

        List<SportTeam> result;

        if (sportTypeIds != null && startDate != null) {
            result = sportTeamRepository.findSportTeamsBySportTypeIdInAndFoundDateBetween(sportTypeIds,
                    startDate,
                    finishDate
            );
        } else if (sportTypeIds != null) {
            result = sportTeamRepository.findSportTeamsBySportTypeIdInOrderByFoundDate(sportTypeIds);
        } else if (startDate != null) {
            result = sportTeamRepository.findSportTeamsByFoundDateBetweenOrderByFoundDate(startDate, finishDate);
        } else {
            result = sportTeamRepository.findAll();
        }

        return result
                .stream()
                .map(sportTeamMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSportTeam(Long id) throws NotFoundException {
        if (sportTeamRepository.existsById(id)){
            throw new NotFoundException("Команда с id ='" + id + "' не найдена");
        }

        sportTeamRepository.deleteById(id);
    }

}
