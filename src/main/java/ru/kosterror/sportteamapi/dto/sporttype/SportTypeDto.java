package ru.kosterror.sportteamapi.dto.sporttype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kosterror.sportteamapi.model.SportType;

/**
 * Класс, который используется для ответа на запросы и хранит в себе информацию о {@link SportType};
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportTypeDto {

    private Long id;

    private String name;

}
