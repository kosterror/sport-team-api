package ru.kosterror.sportteamapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kosterror.sportteamapi.model.TeamMember;

import java.util.List;

/**
 * Интерфейс для взаимодействия с данными в БД, которые представляют собой сущность {@link TeamMember}.
 */
@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    /**
     * Метод для получения участников, которые относятся к одной из определенных команд и имеют одну из
     * определенных ролей.
     *
     * @param teamIds идентификаторы команд.
     * @param roleIds идентификаторы ролей.
     * @return список подходящих участников.
     */
    List<TeamMember> findTeamMembersBySportTeamIdInAndRoleIdIn(List<Long> teamIds, List<Long> roleIds);

    /**
     * Метод для получения участников, которые относятся к одной из определенных команд.
     *
     * @param teamIds идентификаторы команд.
     * @return список подходящих участников.
     */
    List<TeamMember> findTeamMembersBySportTeamIdIn(List<Long> teamIds);

    /**
     * Метод для получения участников, которые имеют одну из определенных ролей.
     *
     * @param roleIds идентификаторы ролей.
     * @return список подходящих участников.
     */
    List<TeamMember> findTeamMembersByRoleIdIn(List<Long> roleIds);

}
