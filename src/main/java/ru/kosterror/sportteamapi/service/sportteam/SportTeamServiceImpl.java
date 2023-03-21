package ru.kosterror.sportteamapi.service.sportteam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kosterror.sportteamapi.dto.sportteam.SportTeamDto;
import ru.kosterror.sportteamapi.exception.BadRequestException;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.mapper.sportteam.SportTeamMapper;
import ru.kosterror.sportteamapi.model.SportTeam;
import ru.kosterror.sportteamapi.repository.SportTeamRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, реализующий интерфейс {@link SportTeamService}.
 */
@Service
@RequiredArgsConstructor
public class SportTeamServiceImpl implements SportTeamService {

    private final SportTeamRepository sportTeamRepository;
    private final SportTeamMapper sportTeamMapper;

    @Override
    public SportTeamDto getSportTeamById(Long id) throws NotFoundException {
        SportTeam sportTeam = sportTeamRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Команда с id = '" + id + "' не найдена"));

        return sportTeamMapper.entityToDto(sportTeam);
    }

    @Override
    public List<SportTeamDto> getSportTeams(List<Long> sportTypeIds,
                                            Date startDate,
                                            Date finishDate
    ) throws BadRequestException {
        if (startDate != null && finishDate == null || startDate == null && finishDate != null) {
            throw new BadRequestException("Необходимо одновременно заполнить или не заполнить поля с датами");
        }

        if (startDate != null && startDate.after(finishDate)) {
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

}
