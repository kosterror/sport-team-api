package ru.kosterror.sportteamapi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Класс для хранения данных в БД об участнике спортивной команды.
 */
@Entity
@Table(name = "team_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMember {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SportTeam sportTeam;

    private String name;

    private String surname;

    private String patronymic;

    @Column(name = "date_of_birth")
    private LocalDate birthDate;

    @ManyToOne
    private TeamMemberRole role;

}
