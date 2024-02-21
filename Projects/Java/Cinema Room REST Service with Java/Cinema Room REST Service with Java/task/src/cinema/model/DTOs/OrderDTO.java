package cinema.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class OrderDTO {
    private final String token;
    private final TicketDTO ticket;

    public OrderDTO() {
        this.token = "";
        this.ticket = null;
    }

    public OrderDTO(@JsonProperty("column") TicketDTO ticket) {
        this.token = UUID.randomUUID().toString();
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public TicketDTO getTicket() {
        return ticket;
    }
}
