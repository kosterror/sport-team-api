package ru.kosterror.sportteamapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sport_type_id", nullable = false)
    private SportType sportType;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_foundation")
    private Date foundDate;

}