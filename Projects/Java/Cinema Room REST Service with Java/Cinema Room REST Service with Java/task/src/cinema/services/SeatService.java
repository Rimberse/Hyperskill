package cinema.services;

import cinema.model.DTOs.TicketDTO;
import cinema.model.DTOs.TokenDTO;
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

    public List<TicketDTO> getAvailableSeats() {
        List<TicketDTO> availableSeats = seatRepository.findAll();
        return availableSeats;
    }

    public TicketDTO purchaseTicket(TicketDTO ticket) {
        int row = ticket.getRow();
        int column = ticket.getColumn();

        if (isValidSeat(row, column)) {
            TicketDTO selectedSeatDTO = seatRepository.findBySeatRowAndSeatColumn(row, column).orElse(null);

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

    public TicketDTO refundTicket(TokenDTO token) {
        TicketDTO selectedSeatDTO = seatRepository.findByToken(token).orElse(null);

        if (selectedSeatDTO != null && bookingService.isAvailable(selectedSeatDTO)) {
            bookingService.book(selectedSeatDTO);
            seatRepository.save(selectedSeatDTO);
            return selectedSeatDTO;
        } else {
            throw new RuntimeException("The ticket has been already purchased!");
        }
    }

    private boolean isValidSeat(int row, int column) {
        return row > 0 && row < 10 && column > 0 && column < 10;
    }
}
