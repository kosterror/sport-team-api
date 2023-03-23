package ru.kosterror.sportteamapi.model;

import lombok.*;

import javax.persistence.*;

/**
 * Класс для хранения данных в БД о роли участника команды.
 */
@Entity
@Table(name = "team_member_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMemberRole {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

}
