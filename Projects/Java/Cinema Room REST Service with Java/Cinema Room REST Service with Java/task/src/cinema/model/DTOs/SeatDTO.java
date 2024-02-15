package cinema.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SeatDTO {
    private final int row;
    private final int column;
    private final int price;

    public SeatDTO() {
        this.row = 0;
        this.column = 0;
        this.price = 0;
    }

    public SeatDTO(@JsonProperty("row") int row, @JsonProperty("column") int column) {
        this.row = row;
        this.column = column;

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
}
