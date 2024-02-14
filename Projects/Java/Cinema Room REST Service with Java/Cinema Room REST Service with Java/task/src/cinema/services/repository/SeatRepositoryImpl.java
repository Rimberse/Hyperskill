package cinema.services.repository;

import cinema.model.DTOs.SeatDTO;
import cinema.services.repository.interfaces.SeatRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SeatRepositoryImpl implements SeatRepository {
    private List<SeatDTO> seats;

    public SeatRepositoryImpl() {
        seats = new ArrayList<>();
    }

    @Override
    public Optional<SeatDTO> findBySeatRowAndSeatColumn(int row, int column) {
        return seats.stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findAny();
    }

    @Override
    public void save(SeatDTO seat) {
        seats.add(seat);
    }
}
