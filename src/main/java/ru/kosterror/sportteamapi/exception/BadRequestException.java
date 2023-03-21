package ru.kosterror.sportteamapi.exception;

/**
 * Исключение, которое будет выбрасываться со статус-кодом 400, если пользователь ввёл некорректные данные.
 */
public class BadRequestException extends Exception {

    /**
     * Конструктор.
     *
     * @param message текст об исключении.
     */
    public BadRequestException(String message) {
        super(message);
    }
}
