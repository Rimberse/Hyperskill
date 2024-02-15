package cinema.model.DTOs;

import java.util.List;

public class SeatResponseDTO {
    private int rows;
    private int columns;
    private List<SeatDTO> seats;

    public SeatResponseDTO(List<SeatDTO> seats) {
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

    public List<SeatDTO> getSeats() {
        return seats;
    }
}
