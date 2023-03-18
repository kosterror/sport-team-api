package ru.kosterror.sportteamapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kosterror.sportteamapi.model.SportType;

/**
 * Интерфейс для взаимодействия с данными в БД, которые представляют собой сущность {@link SportType}.
 */
@Repository
public interface SportTypeRepository extends JpaRepository<SportType, Long> {
}
