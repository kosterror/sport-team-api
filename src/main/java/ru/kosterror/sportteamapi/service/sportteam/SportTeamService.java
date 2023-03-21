package ru.kosterror.sportteamapi.service.sportteam;

import ru.kosterror.sportteamapi.dto.sportteam.CreateUpdateSportTeamDto;
import ru.kosterror.sportteamapi.dto.sportteam.SportTeamDto;
import ru.kosterror.sportteamapi.exception.BadRequestException;
import ru.kosterror.sportteamapi.exception.ConflictException;
import ru.kosterror.sportteamapi.exception.NotFoundException;
import ru.kosterror.sportteamapi.model.SportTeam;

import java.util.Date;
import java.util.List;

/**
 * Интерфейс для взаимодействия с {@link SportTeam}.
 */
public interface SportTeamService {

    /**
     * Метод для получения списка команд. Все параметры могут быть {@code null}. <strong>Но {@code startDate} и
     * {@code finishDate} должны быть одновременно {@code null} или не {@code null}.
     * Причем, {@code startDate < finishDate} </strong>.
     *
     * @param sportTypeIds список идентификаторов видов спорта. Может быть {@code null}.
     * @param startDate    начальная дата периода для поиска команд по дате основания.
     * @param finishDate   конечная дата периода для поиска команд по дате основания.
     * @return список найденных команд, удовлетворяющих условиям.
     * @throws BadRequestException возникает, если входные данные некорректны.
     */
    List<SportTeamDto> getSportTeams(List<Long> sportTypeIds, Date startDate, Date finishDate) throws BadRequestException;

    /**
     * Метод для получения информации о команде по её идентификатору.
     *
     * @param id идентификатор команды.
     * @return информацию о команде.
     * @throws NotFoundException возникает, если команда по идентификатору не найдена.
     */
    SportTeamDto getSportTeamById(Long id) throws NotFoundException;

    /**
     * Метод для создания и сохранения новой спортивной команды.
     *
     * @param createUpdateSportTeamDto данные для создания спортивной команды.
     * @return информация о созданной спортивной команде.
     * @throws NotFoundException возникает, если вид спорта по указанному идентификатору не найден.
     * @throws ConflictException возникает, если название спортивной команды занято.
     */
    SportTeamDto createSportTeam(
            CreateUpdateSportTeamDto createUpdateSportTeamDto
    ) throws NotFoundException, ConflictException;

}
