package cinema.model.repository.interfaces;

import cinema.model.DTOs.SeatDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository {
    List<SeatDTO> findAll();
    Optional<SeatDTO> findBySeatRowAndSeatColumn(int row, int column);
    void save(SeatDTO seat);
}
