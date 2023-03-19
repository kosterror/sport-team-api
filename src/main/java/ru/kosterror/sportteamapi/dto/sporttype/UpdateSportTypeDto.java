package ru.kosterror.sportteamapi.dto.sporttype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kosterror.sportteamapi.model.SportType;

/**
 * Класс, который используется для ответа на запросы и хранит в себе информацию для обновления {@link SportType};
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSportTypeDto {

    private String sportName;

}
