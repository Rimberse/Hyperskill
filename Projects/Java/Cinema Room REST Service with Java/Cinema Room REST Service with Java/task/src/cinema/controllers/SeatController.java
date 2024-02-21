package cinema.controllers;

import cinema.model.DTOs.OrderDTO;
import cinema.model.DTOs.SeatResponseDTO;
import cinema.model.DTOs.TicketDTO;
import cinema.model.DTOs.TokenDTO;
import cinema.model.DTOs.errors.ErrorDTO;
import cinema.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class SeatController {
    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/seats")
    public ResponseEntity<SeatResponseDTO> getAvailableSeats() {
        List<TicketDTO> availableSeats = seatService.getAvailableSeats();
        SeatResponseDTO seatResponseDTO = new SeatResponseDTO(availableSeats);
        return ResponseEntity.ok(seatResponseDTO);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody TicketDTO ticketDTO) {
        try {
            TicketDTO ticket = seatService.purchaseTicket(ticketDTO);
            TokenDTO token = new TokenDTO();
            OrderDTO order = new OrderDTO(token, ticket);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }
}
