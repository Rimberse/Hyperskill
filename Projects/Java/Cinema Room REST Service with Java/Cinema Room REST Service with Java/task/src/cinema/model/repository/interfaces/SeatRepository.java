package cinema.model.repository.interfaces;

import cinema.model.DTOs.TicketDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository {
    List<TicketDTO> findAll();
    Optional<TicketDTO> findBySeatRowAndSeatColumn(int row, int column);
    void save(TicketDTO ticket);
}
