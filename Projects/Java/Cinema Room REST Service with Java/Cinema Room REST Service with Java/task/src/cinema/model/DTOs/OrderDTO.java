package cinema.model.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record OrderDTO(@JsonInclude(JsonInclude.Include.NON_NULL) String token, TicketDTO ticket) {
    public OrderDTO(@JsonProperty("ticket") TicketDTO ticket) {
        this(UUID.randomUUID().toString(), ticket);
    }

    public OrderDTO(@JsonProperty("token") String token, @JsonProperty("ticket") TicketDTO ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    @Override
    public String token() {
        return token;
    }

    @Override
    public TicketDTO ticket() {
        return ticket;
    }
}
