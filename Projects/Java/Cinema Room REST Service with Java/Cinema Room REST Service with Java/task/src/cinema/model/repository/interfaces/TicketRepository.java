package cinema.model.repository.interfaces;

import cinema.model.DTOs.OrderDTO;
import cinema.model.DTOs.TicketDTO;
import cinema.model.DTOs.TokenDTO;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository {
    List<TicketDTO> findAll();
    Optional<TicketDTO> findBySeatRowAndSeatColumn(int row, int column);
    Optional<OrderDTO> findByToken(TokenDTO token);
    void save(OrderDTO order);
    void delete(TokenDTO token);
}
