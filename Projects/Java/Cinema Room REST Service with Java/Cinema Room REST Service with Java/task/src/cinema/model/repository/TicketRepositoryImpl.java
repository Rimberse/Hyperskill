package cinema.model.repository;

import cinema.model.DTOs.TicketDTO;
import cinema.model.repository.interfaces.TicketRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
    private boolean[][] booked;

    public TicketRepositoryImpl() {
        booked = new boolean[9][9];
    }

    @Override
    public boolean findForSeatRowAndSeatColumn(TicketDTO ticket) {
        return booked[ticket.getRow() - 1][ticket.getColumn() - 1];
    }

    @Override
    public void save(TicketDTO ticket) {
        booked[ticket.getRow() - 1][ticket.getColumn() - 1] = true;
    }
}
