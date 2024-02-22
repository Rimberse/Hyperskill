package cinema.model.exceptions;

public class TicketPurchasedException extends RuntimeException {
    private String message;

    public TicketPurchasedException() {
        super("The ticket has been already purchased!");
        this.message = "The ticket has been already purchased!";
    }

    public String getError() {
        return message;
    }
}
