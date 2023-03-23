package ru.kosterror.sportteamapi.dto.sporttype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kosterror.sportteamapi.model.SportType;

/**
 * Класс, который хранит в себе минимальную информацию для создания и сохранения в БД {@link SportType}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewSportTypeDto {

    private String sportName;

}
