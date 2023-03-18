package ru.kosterror.sportteamapi.model;

import lombok.*;

import javax.persistence.*;

/**
 * Класс для хранения данных в БД о виде спорта.
 */
@Entity
@Table(name = "sport_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

}
