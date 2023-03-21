package ru.kosterror.sportteamapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kosterror.sportteamapi.model.SportTeam;

import java.util.Date;
import java.util.List;

/**
 * Интерфейс для взаимодействия с данными в БД, которые представляют собой сущность {@link SportTeam}.
 */
@Repository
public interface SportTeamRepository extends JpaRepository<SportTeam, Long> {

    /**
     * Найти команды, которые основаны в определенный временной промежуток и относятся к перечисленным видам спорта.
     *
     * @param sportTypeIds список идентификаторов видов спорта.
     * @param startDate    начальная дата периода.
     * @param finishDate   конечная дата периода.
     * @return список подходящих команд.
     */
    List<SportTeam> findSportTeamsBySportTypeIdInAndFoundDateBetween(List<Long> sportTypeIds,
                                                                     Date startDate,
                                                                     Date finishDate
    );

    /**
     * Метод для получения команд, которые относятся к определенным видам спорта.
     *
     * @param sportTypeIds список идентификаторов видов спорта.
     * @return список подходящих команд.
     */
    List<SportTeam> findSportTeamsBySportTypeIdInOrderByFoundDate(List<Long> sportTypeIds);

    /**
     * Метод для получения команд, которые были основаны в определенный временной период.
     *
     * @param startDate  начальная дата периода.
     * @param finishDate конечная дата периода.
     * @return список подходящих команд.
     */
    List<SportTeam> findSportTeamsByFoundDateBetweenOrderByFoundDate(Date startDate, Date finishDate);

}
