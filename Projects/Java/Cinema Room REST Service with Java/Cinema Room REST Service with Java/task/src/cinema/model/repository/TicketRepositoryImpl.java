package cinema.model.repository;

import cinema.model.DTOs.OrderDTO;
import cinema.model.DTOs.TicketDTO;
import cinema.model.repository.interfaces.TicketRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
    private List<TicketDTO> tickets;
    private Map<String, OrderDTO> orders;

    public TicketRepositoryImpl() {
        tickets = new ArrayList<>();
        this.orders = new ConcurrentHashMap<>();

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                tickets.add(new TicketDTO(row + 1, column + 1));
            }
        }
    }

    @Override
    public List<TicketDTO> findAll() {
        return tickets;
    }

    @Override
    public Optional<TicketDTO> findBySeatRowAndSeatColumn(int row, int column) {
        return tickets
                .stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findAny();
    }

    @Override
    public Optional<OrderDTO> findByToken(String token) {
        return Optional.ofNullable(orders.getOrDefault(token, null));
    }

    @Override
    public void save(OrderDTO order) {
        orders.putIfAbsent(order.token(), order);
    }

    @Override
    public void delete(String token) {
        orders.remove(token);
    }
}
