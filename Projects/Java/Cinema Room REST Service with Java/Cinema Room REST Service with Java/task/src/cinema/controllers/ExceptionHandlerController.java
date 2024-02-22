package cinema.controllers;

import cinema.model.errors.ErrorMessage;
import cinema.model.exceptions.SeatNumberOutOfBoundsException;
import cinema.model.exceptions.TicketPurchasedException;
import cinema.model.exceptions.WrongTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(TicketPurchasedException.class)
    public ResponseEntity<ErrorMessage> handleTicketHasBeenPurchased(
            TicketPurchasedException e, WebRequest request) {

        ErrorMessage body = new ErrorMessage(
//                HttpStatus.BAD_REQUEST.value(),
                0,
//                LocalDateTime.now(),
                null,
//                e.getMessage(),
                null,
//                request.getDescription(false),
                null,
                e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatNumberOutOfBoundsException.class)
    public ResponseEntity<ErrorMessage> handleRowOrColumnOutOfBounds(
            SeatNumberOutOfBoundsException e, WebRequest request) {

        ErrorMessage body = new ErrorMessage(
//                HttpStatus.BAD_REQUEST.value(),
                0,
//                LocalDateTime.now(),
                null,
//                e.getMessage(),
                null,
//                request.getDescription(false),
                null,
                e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTokenException.class)
    public ResponseEntity<ErrorMessage> handleWrongToken(
            WrongTokenException e, WebRequest request) {

        ErrorMessage body = new ErrorMessage(
//                HttpStatus.BAD_REQUEST.value(),
                0,
//                LocalDateTime.now(),
                null,
//                e.getMessage(),
                null,
//                request.getDescription(false),
                null,
                e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
