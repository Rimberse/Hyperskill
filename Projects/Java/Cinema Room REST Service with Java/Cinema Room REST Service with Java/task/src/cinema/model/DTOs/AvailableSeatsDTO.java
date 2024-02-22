package cinema.model.DTOs;

import java.util.List;

public class AvailableSeatsDTO {
    private int rows;
    private int columns;
    private List<TicketDTO> seats;

    public AvailableSeatsDTO(List<TicketDTO> seats) {
        this.rows = 9;
        this.columns = 9;
        this.seats = seats;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<TicketDTO> getSeats() {
        return seats;
    }
}
