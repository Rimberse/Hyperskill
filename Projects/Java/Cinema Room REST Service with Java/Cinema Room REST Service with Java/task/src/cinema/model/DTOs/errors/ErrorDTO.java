package cinema.model.DTOs.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDTO {
    private String error;

    public ErrorDTO() {
        this.error = "";
    }

    public ErrorDTO(@JsonProperty("error") String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
