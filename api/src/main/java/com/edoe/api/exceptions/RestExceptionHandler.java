package com.edoe.api.exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;

/*   Para dizer ao spring que essa é uma classe especializada em tratar erros.
 *   @RestControllerAdvice é a combinação de @ControllerAdvice e @ResponseBody
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${app.url.base.dev}")
    private String urlBaseLocal;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> globalException(Exception exception, WebRequest request) {
        exception.printStackTrace();
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getDescription(false),
                "Internal server error",
                exception.getMessage()
                );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> badRequestException(BadRequestException exception, WebRequest request) {
        exception.printStackTrace();
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                this.urlBaseLocal,
                exception.getMessage(),
                exception.getDescription());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> emailNotFoundException(EmailNotFoundException exception, WebRequest request) {
        exception.printStackTrace();
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                this.urlBaseLocal,
                exception.getMessage(),
                exception.getDescription());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoginNotDone.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ErrorMessage> loginNotDoneException(LoginNotDone exception, WebRequest request) {
        exception.printStackTrace();
        ErrorMessage message = new ErrorMessage(
                HttpStatus.OK.value(),
                this.urlBaseLocal,
                exception.getMessage(),
                exception.getDescription());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorMessage> forbiddenException(ForbiddenException exception, WebRequest request) {
        exception.printStackTrace();
        ErrorMessage message = new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                this.urlBaseLocal,
                exception.getMessage(),
                exception.getDescription());
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DescriptorAlreadyRegisteredException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> forbiddenException(DescriptorAlreadyRegisteredException exception, WebRequest request) {
        exception.printStackTrace();
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                this.urlBaseLocal,
                exception.getMessage(),
                exception.getDescription());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> notFoundException(NotFoundException exception, WebRequest request) {
        exception.printStackTrace();
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                this.urlBaseLocal,
                exception.getMessage(),
                exception.getDescription());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
