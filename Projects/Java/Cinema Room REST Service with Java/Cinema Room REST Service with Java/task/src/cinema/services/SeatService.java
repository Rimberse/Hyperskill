package cinema.services;

import cinema.model.DTOs.SeatDTO;
import cinema.model.repository.interfaces.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<SeatDTO> getAvailableSeats() {
        List<SeatDTO> availableSeats = seatRepository.findAll();
        return availableSeats;
    }

    private boolean isValidSeat(int row, int column) {
        return row > 0 && row < 10 && column > 0 && column < 10;
    }
}
