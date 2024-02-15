package cinema.model.repository;

import cinema.model.DTOs.SeatDTO;
import cinema.model.repository.interfaces.TicketRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
    private boolean[][] booked;

    public TicketRepositoryImpl() {
        booked = new boolean[9][9];
    }

    @Override
    public boolean findForSeatRowAndSeatColumn(SeatDTO seatDTO) {
        return booked[seatDTO.getRow() - 1][seatDTO.getColumn() - 1];
    }

    @Override
    public void save(SeatDTO seatDTO) {
        booked[seatDTO.getRow() - 1][seatDTO.getColumn() - 1] = true;
    }
}
