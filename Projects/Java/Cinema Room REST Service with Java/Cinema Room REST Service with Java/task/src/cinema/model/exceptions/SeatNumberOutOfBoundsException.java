package cinema.model.exceptions;

public class SeatNumberOutOfBoundsException extends RuntimeException {
    private String message;

    public SeatNumberOutOfBoundsException() {
        super("The number of a row or a column is out of bounds!");
        this.message = "The number of a row or a column is out of bounds!";
    }

    public String getError() {
        return message;
    }
}
