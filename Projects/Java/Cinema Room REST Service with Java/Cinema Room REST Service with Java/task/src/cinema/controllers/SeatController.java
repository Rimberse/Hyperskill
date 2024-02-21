package cinema.controllers;

import cinema.model.DTOs.OrderDTO;
import cinema.model.DTOs.SeatResponseDTO;
import cinema.model.DTOs.TicketDTO;
import cinema.model.DTOs.TokenDTO;
import cinema.model.DTOs.errors.ErrorDTO;
import cinema.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class SeatController {
    private final TicketService ticketService;

    @Autowired
    public SeatController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/seats")
    public ResponseEntity<SeatResponseDTO> getAvailableSeats() {
        List<TicketDTO> availableSeats = ticketService.getAvailableSeats();
        SeatResponseDTO seatResponseDTO = new SeatResponseDTO(availableSeats);
        return ResponseEntity.ok(seatResponseDTO);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody TicketDTO ticketDTO) {
        try {
            OrderDTO order = ticketService.purchaseTicket(ticketDTO);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> refundTicket(@RequestBody TokenDTO tokenDTO) {
        try {
            OrderDTO order = ticketService.refundTicket(tokenDTO);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }
}
