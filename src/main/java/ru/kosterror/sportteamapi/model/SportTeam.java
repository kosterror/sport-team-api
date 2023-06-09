package ru.kosterror.sportteamapi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Класс для хранения данных в БД о спортивной команде.
 */
@Entity
@Table(name = "sport_team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportTeam {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_type_id")
    private SportType sportType;

    @Column(name = "date_of_foundation")
    private LocalDate foundDate;

    @OneToMany(mappedBy = "sportTeam", fetch = FetchType.EAGER)
    private List<TeamMember> members;

}
