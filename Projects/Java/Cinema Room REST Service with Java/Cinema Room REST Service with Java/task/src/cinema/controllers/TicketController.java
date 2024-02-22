package cinema.controllers;

import cinema.model.DTOs.OrderDTO;
import cinema.model.DTOs.AvailableSeatsDTO;
import cinema.model.DTOs.TicketDTO;
import cinema.model.DTOs.TokenDTO;
import cinema.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/seats")
    public ResponseEntity<AvailableSeatsDTO> getAvailableSeats() {
        List<TicketDTO> availableSeats = ticketService.getAvailableSeats();
        AvailableSeatsDTO seatResponseDTO = new AvailableSeatsDTO(availableSeats);
        return ResponseEntity.ok(seatResponseDTO);
    }

    @PostMapping("/purchase")
    public ResponseEntity<OrderDTO> purchaseTicket(@RequestBody TicketDTO ticketDTO) {
        OrderDTO order = ticketService.purchaseTicket(ticketDTO);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/return")
    public ResponseEntity<OrderDTO> refundTicket(@RequestBody TokenDTO tokenDTO) {
        OrderDTO order = ticketService.refundTicket(tokenDTO.getToken());
        return ResponseEntity.ok(order);
    }
}
