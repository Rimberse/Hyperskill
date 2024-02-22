package cinema.model.exceptions;

public class WrongTokenException extends RuntimeException {
    private String message;

    public WrongTokenException() {
        super("Wrong token!");
        this.message = "Wrong token!";
    }

    public String getError() {
        return message;
    }
}
