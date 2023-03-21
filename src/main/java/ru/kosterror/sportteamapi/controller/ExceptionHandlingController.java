package ru.kosterror.sportteamapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.kosterror.sportteamapi.dto.ApiError;
import ru.kosterror.sportteamapi.exception.BadRequestException;
import ru.kosterror.sportteamapi.exception.ConflictException;
import ru.kosterror.sportteamapi.exception.NotFoundException;

import javax.naming.ConfigurationException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Контроллер для глобальной обработки исключений. Здесь будут отлавливаться все исключения,
 * которые могут дойти до контроллера.
 */
@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    private static final String INCORRECT_INPUT_DATA = "Некорректные входные данные";
    private static final String INTERNAL_SERVER_ERROR = "Внутрення ошибка сервера";

    /**
     * Метод для отлавливания {@link BadRequestException}.
     *
     * @param request   запрос, во время которого выбросилось исключение.
     * @param exception исключение, которое будет обрабатываться.
     * @return {@link ApiError}, обернутый в {@link ResponseEntity} с кодом 400.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(HttpServletRequest request,
                                                              BadRequestException exception
    ) {
        ApiError error = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                INCORRECT_INPUT_DATA,
                Stream.of(exception.getMessage()).collect(Collectors.toList())
        );

        return new ResponseEntity<>(error, error.getHttpStatus());
    }

    /**
     * Метод для отлавливания {@link NotFoundException}.
     *
     * @param request   запрос, во время которого выбросилось исключение.
     * @param exception исключение, которое будет обрабатываться.
     * @return {@link ApiError}, обернутый в {@link ResponseEntity} с кодом 404.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(HttpServletRequest request,
                                                            NotFoundException exception
    ) {
        ApiError error = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                INCORRECT_INPUT_DATA,
                Stream.of(exception.getMessage()).collect(Collectors.toList())
        );

        return new ResponseEntity<>(error, error.getHttpStatus());
    }

    /**
     * Метод для отлавливания {@link ConfigurationException}.
     *
     * @param request   запрос, во время которого выбросилось исключение.
     * @param exception исключение, которое будет обрабатываться.
     * @return {@link ApiError}, обернутый в {@link ResponseEntity} с кодом 409.
     */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleConflictException(HttpServletRequest request, ConflictException exception) {
        ApiError error = new ApiError(
                LocalDateTime.now(),
                HttpStatus.CONFLICT,
                INCORRECT_INPUT_DATA,
                Stream.of(exception.getMessage()).collect(Collectors.toList())
        );

        return new ResponseEntity<>(error, error.getHttpStatus());
    }

    /**
     * Метод для отлавливания {@link Exception}.
     *
     * @param request   запрос, во время которого выбросилось исключение.
     * @param exception исключение, которое будет обрабатываться.
     * @return {@link ApiError}, обернутый в {@link ResponseEntity} с кодом 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(HttpServletRequest request, Exception exception) {
        ApiError error = new ApiError(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                INTERNAL_SERVER_ERROR,
                Collections.emptyList()
        );

        return new ResponseEntity<>(error, error.getHttpStatus());
    }

}
