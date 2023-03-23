package ru.kosterror.sportteamapi.exception;

/**
 * Исключение, которое будет выбрасываться при противоречивых ситуациях. Например, попытка создать спортивную команду
 * с занятым названием.
 */
public class ConflictException extends Exception {

    /**
     * Конструктор.
     *
     * @param message текст исключения.
     */
    public ConflictException(String message) {
        super(message);
    }

}
