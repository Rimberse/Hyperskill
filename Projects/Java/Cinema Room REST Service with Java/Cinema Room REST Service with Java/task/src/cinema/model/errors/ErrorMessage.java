package cinema.model.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

public record ErrorMessage(@JsonInclude(JsonInclude.Include.NON_DEFAULT) int statusCode,
                           @JsonInclude(JsonInclude.Include.NON_NULL) LocalDateTime timestamp,
                           @JsonInclude(JsonInclude.Include.NON_NULL) String message,
                           @JsonInclude(JsonInclude.Include.NON_NULL) String description,
                           @JsonInclude(JsonInclude.Include.NON_NULL) String error) {
    public ErrorMessage(int statusCode, LocalDateTime timestamp, String message, String description, String error) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
        this.error = error;
    }

    @Override
    public int statusCode() {
        return statusCode;
    }

    @Override
    public LocalDateTime timestamp() {
        return timestamp;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String error() {
        return error;
    }
}
