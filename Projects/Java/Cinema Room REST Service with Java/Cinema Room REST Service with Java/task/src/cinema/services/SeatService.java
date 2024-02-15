package cinema.services;

import cinema.model.DTOs.SeatDTO;
import cinema.model.repository.interfaces.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    private final SeatRepository seatRepository;
    private final BookingService bookingService;

    @Autowired
    public SeatService(SeatRepository seatRepository, BookingService bookingService) {
        this.seatRepository = seatRepository;
        this.bookingService = bookingService;
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

            if (selectedSeatDTO != null && bookingService.isAvailable(selectedSeatDTO)) {
                bookingService.book(selectedSeatDTO);
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
