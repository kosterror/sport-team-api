package ru.kosterror.sportteamapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.kosterror.sportteamapi.dto.sporttype.ApiError;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;

/**
 * Контроллер для глобальной обработки исключений. Здесь будут отлавливаться все исключения,
 * которые могут дойти до контроллера.
 */
@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

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
                "Внутрення ошибка сервера",
                Collections.emptyList()
        );

        return new ResponseEntity<>(error, error.getHttpStatus());
    }

}
