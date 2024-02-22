package cinema.services;

import cinema.model.DTOs.OrderDTO;
import cinema.model.DTOs.TicketDTO;
import cinema.model.exceptions.SeatNumberOutOfBoundsException;
import cinema.model.exceptions.TicketPurchasedException;
import cinema.model.exceptions.WrongTokenException;
import cinema.model.repository.interfaces.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<TicketDTO> getAvailableSeats() {
        List<TicketDTO> availableSeats = ticketRepository.findAll();
        return availableSeats;
    }

    public OrderDTO purchaseTicket(TicketDTO ticket) {
        int row = ticket.getRow();
        int column = ticket.getColumn();

        if (isValidSeat(row, column)) {
            TicketDTO selectedSeat = ticketRepository.findBySeatRowAndSeatColumn(row, column).orElse(null);

            if (selectedSeat != null && !selectedSeat.isBooked()) {
                OrderDTO order = new OrderDTO(selectedSeat);
                selectedSeat.setBooked(true);
                ticketRepository.save(order);
                return order;
            } else {
                throw new TicketPurchasedException();
            }
        } else {
            throw new SeatNumberOutOfBoundsException();
        }
    }

    public OrderDTO refundTicket(String token) {
        OrderDTO order = ticketRepository.findByToken(token).orElse(null);

        if (order != null) {
            ticketRepository.delete(token);
            TicketDTO ticket = order.ticket();
            ticket.setBooked(false);
            return new OrderDTO(null, ticket);
        } else {
            throw new WrongTokenException();
        }
    }

    private boolean isValidSeat(int row, int column) {
        return row > 0 && row < 10 && column > 0 && column < 10;
    }
}
