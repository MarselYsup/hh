package com.technokratos.controllers.handlers;

import com.technokratos.exceptions.main.HhServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class HhExceptionHandler {

    /** Сообщение при ошибки валидации */
    private final static String ERROR_VALID_MESSAGE = "Validation error";

    /** Формирует ответ на основе всех исключений наследованных от HhServiceException
     *
     * @param e - исключение
     * @param request - запрос, на котором выпало исключение
     *
     * @return полная информация ошибки в виде JSON
     * */

    @ExceptionHandler(value = HhServiceException.class)
    public ResponseEntity<Object> handleHhException(HhServiceException e, HttpServletRequest request){
        return getError(e, request);
    }

    /** Формирует ответ на основе исключений MethodArgumentNotValidException,
     * связанных с валидацией
     *
     * @param e - исключение
     * @param request - запрос, на котором выпало исключение
     *
     * @return полная информация ошибки в виде JSON
     * */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorValidInfo> handleValidException(MethodArgumentNotValidException e,
                                                              HttpServletRequest request) {

        ErrorValidInfo errorValidDto = ErrorValidInfo.builder()
                .code(HttpStatus.BAD_REQUEST)
                .message(ERROR_VALID_MESSAGE)
                .validErrors(processFieldErrors(e.getBindingResult()))
                .path(request.getRequestURI())
                .exceptionName(e.getClass().getSimpleName())
                .build();

        return new ResponseEntity<>(errorValidDto, errorValidDto.getCode());
    }

    /** Формирует ответ на основе исключения AccessDeniedException
     *
     * @param e - исключение
     * @param request - запрос, на котором выпало исключение
     *
     * @return полная информация ошибки в виде JSON
     * */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        return getError(e, request);
    }

    private List<ValidError> processFieldErrors(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map( e -> {
                    if (e instanceof FieldError) {
                        FieldError error = (FieldError) e;
                        return new ValidError(error.getField(),error.getDefaultMessage());
                    }
                    return new ValidError(e.getObjectName(), e.getDefaultMessage());
                })
                .collect(Collectors.toList());

    }

    /**
     *  Обработка кастомных исключений
     * */
    private ResponseEntity<Object> getError(HhServiceException e, HttpServletRequest request) {

        ErrorInfo errorInfo = ErrorInfo.builder()
                .message(e.getMessage())
                .httpStatus(e.getHttpStatus())
                .exceptionName(e.getClass().getSimpleName())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(errorInfo, e.getHttpStatus());
    }

    /**
     *  Обработка исключений насдледников Exception
     * */
    private ResponseEntity<Object> getError(Exception e, HttpServletRequest request) {

        ErrorInfo errorInfo = ErrorInfo.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exceptionName(e.getClass().getSimpleName())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
