package cinema.model.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketDTO {
    private final int row;
    private final int column;
    private final int price;
    private boolean isBooked;

    public TicketDTO(@JsonProperty("row") int row, @JsonProperty("column") int column) {
        this.row = row;
        this.column = column;
        this.isBooked = false;

        if (row < 5) {
            this.price = 10;
        } else {
            this.price = 8;
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    @JsonIgnore
    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }
}
