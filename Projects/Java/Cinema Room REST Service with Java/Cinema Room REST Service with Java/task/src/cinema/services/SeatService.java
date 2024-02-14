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

    public SeatDTO purchaseTicket(SeatDTO ticketDTO) {
        int row = ticketDTO.getRow();
        int column = ticketDTO.getColumn();

        if (isValidSeat(row, column)) {
            SeatDTO selectedSeatDTO = seatRepository.findBySeatRowAndSeatColumn(row, column).orElse(null);

            if (selectedSeatDTO != null && selectedSeatDTO.getPrice() > 0) {
                selectedSeatDTO.setPrice(0);
                seatRepository.save(selectedSeatDTO);

                return selectedSeatDTO;
            } else {
                throw new RuntimeException("The ticket has been already purchased!");
            }
        } else {
            throw new RuntimeException("The number of a row or a column is out of bounds!");
        }
    }

    private boolean isValidSeat(int row, int column) {
        return row > 0 && row < 10 && column > 0 && column < 10;
    }
}
