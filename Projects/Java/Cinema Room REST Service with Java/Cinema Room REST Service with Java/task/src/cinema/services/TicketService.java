package cinema.services;

import cinema.model.DTOs.OrderDTO;
import cinema.model.DTOs.TicketDTO;
import cinema.model.DTOs.TokenDTO;
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
                TokenDTO token = new TokenDTO();
                OrderDTO order = new OrderDTO(token, selectedSeat);
                selectedSeat.setBooked(true);
                ticketRepository.save(order);
                return order;
            } else {
                throw new RuntimeException("The ticket has been already purchased!");
            }
        } else {
            throw new RuntimeException("The number of a row or a column is out of bounds!");
        }
    }

    public OrderDTO refundTicket(TokenDTO token) {
        OrderDTO order = ticketRepository.findByToken(token).orElse(null);

        if (order != null) {
            ticketRepository.delete(token);
            TicketDTO ticket = order.getTicket();
            ticket.setBooked(false);
            return new OrderDTO(null, ticket);
        } else {
            throw new RuntimeException("Wrong token!");
        }
    }

    private boolean isValidSeat(int row, int column) {
        return row > 0 && row < 10 && column > 0 && column < 10;
    }
}
