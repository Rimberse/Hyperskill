package cinema.services;

import cinema.model.DTOs.SeatDTO;
import cinema.model.repository.interfaces.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final TicketRepository ticketRepository;

    @Autowired
    public BookingService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public boolean isAvailable(SeatDTO seatDTO) {
        return !ticketRepository.findForSeatRowAndSeatColumn(seatDTO);
    }

    public void book(SeatDTO seatDTO) {
        ticketRepository.save(seatDTO);
    }
}
