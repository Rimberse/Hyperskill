package cinema.services;

import cinema.model.DTOs.TicketDTO;
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

    public boolean isAvailable(TicketDTO ticket) {
        return !ticketRepository.findForSeatRowAndSeatColumn(ticket);
    }

    public void book(TicketDTO ticket) {
        ticketRepository.save(ticket);
    }
}
