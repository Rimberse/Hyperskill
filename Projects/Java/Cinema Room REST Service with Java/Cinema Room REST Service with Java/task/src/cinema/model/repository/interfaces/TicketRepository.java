package cinema.model.repository.interfaces;

import cinema.model.DTOs.SeatDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository {
    boolean findForSeatRowAndSeatColumn(SeatDTO seatDTO);
    void save(SeatDTO seatDTO);
}
