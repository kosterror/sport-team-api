package ru.kosterror.sportteamapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kosterror.sportteamapi.model.TeamMemberRole;

/**
 * Интерфейс для взаимодействия с данными в БД, которые представляют собой сущность {@link TeamMemberRole}.
 */
@Repository
public interface TeamMemberRoleRepository extends JpaRepository<TeamMemberRole, Long> {

    boolean existsByName(String name);

}
