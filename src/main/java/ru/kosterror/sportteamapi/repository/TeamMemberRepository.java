package ru.kosterror.sportteamapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kosterror.sportteamapi.model.TeamMember;

/**
 * Интерфейс для взаимодействия с данными в БД, которые представляют собой сущность {@link TeamMember}.
 */
@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
}
