package ru.kosterror.sportteamapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kosterror.sportteamapi.model.SportTeam;

/**
 * Интерфейс для взаимодействия с данными в БД, которые представляют собой сущность {@link SportTeam}.
 */
@Repository
public interface SportTeamRepository extends JpaRepository<SportTeam, Long> {
}
