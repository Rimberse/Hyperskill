package cinema.model.repository;

import cinema.model.DTOs.TicketDTO;
import cinema.model.repository.interfaces.SeatRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SeatRepositoryImpl implements SeatRepository {
    private List<TicketDTO> tickets;

    public SeatRepositoryImpl() {
        tickets = new ArrayList<>();

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                tickets.add(new TicketDTO(row + 1, column + 1));
            }
        }
    }

    @Override
    public List<TicketDTO> findAll() {
        return tickets;
    }

    @Override
    public Optional<TicketDTO> findBySeatRowAndSeatColumn(int row, int column) {
        return tickets.stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findAny();
    }

    @Override
    public void save(TicketDTO ticket) {
        tickets.add(ticket);
    }
}
