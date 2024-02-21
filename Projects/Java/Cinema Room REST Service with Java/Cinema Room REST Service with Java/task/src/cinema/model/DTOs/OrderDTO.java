package cinema.model.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class OrderDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final TokenDTO token;
    private final TicketDTO ticket;

    public OrderDTO() {
        this.token = null;
        this.ticket = null;
    }

    public OrderDTO(@JsonProperty("column") TokenDTO token, @JsonProperty("column") TicketDTO ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public TokenDTO getToken() {
        return token;
    }

    public TicketDTO getTicket() {
        return ticket;
    }
}
