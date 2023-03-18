package ru.kosterror.sportteamapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kosterror.sportteamapi.model.SportType;

/**
 * Интерфейс для взаимодействия с данными в БД, которые представляют собой сущность {@link SportType}.
 */
public interface SportTypeRepository extends JpaRepository<SportType, Long> {
}
