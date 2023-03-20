package ru.kosterror.sportteamapi.exception;

/**
 * Исключение, которое будет выбрасываться при ненахождении информации с заданными данными.
 */
public class NotFoundException extends Exception {

    /**
     * Конструктор.
     * @param message текст исключения.
     */
    public NotFoundException(String message) {
        super(message);
    }

}
