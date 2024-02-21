package cinema.model.repository.interfaces;

import cinema.model.DTOs.TicketDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository {
    boolean findForSeatRowAndSeatColumn(TicketDTO ticket);
    void save(TicketDTO ticket);
}
