package cinema.services.repository.interfaces;

import cinema.model.DTOs.SeatDTO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository {
    Optional<SeatDTO> findBySeatRowAndSeatColumn(int row, int column);
    void save(SeatDTO seat);
}
