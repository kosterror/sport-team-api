package ru.kosterror.sportteamapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kosterror.sportteamapi.model.SportTeam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс для взаимодействия с данными в БД, которые представляют собой сущность {@link SportTeam}.
 */
@Repository
public interface SportTeamRepository extends JpaRepository<SportTeam, Long> {

    /**
     * Метод для проверки существования спортивной команды с заданным именем.
     *
     * @param name название команды.
     * @return существует ли спортивная команда с заданным именем.
     */
    boolean existsByName(String name);

    /**
     * Метод для получения команды по её названию.
     *
     * @param name название команды.
     * @return найденная команда.
     */
    Optional<SportTeam> findByName(String name);

    /**
     * Найти команды, которые основаны в определенный временной промежуток и относятся к перечисленным видам спорта.
     *
     * @param sportTypeIds список идентификаторов видов спорта.
     * @param startDate    начальная дата периода.
     * @param finishDate   конечная дата периода.
     * @return список подходящих команд.
     */
    List<SportTeam> findSportTeamsBySportTypeIdInAndFoundDateBetween(List<Long> sportTypeIds,
                                                                     LocalDate startDate,
                                                                     LocalDate finishDate
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
    List<SportTeam> findSportTeamsByFoundDateBetweenOrderByFoundDate(LocalDate startDate, LocalDate finishDate);

}
