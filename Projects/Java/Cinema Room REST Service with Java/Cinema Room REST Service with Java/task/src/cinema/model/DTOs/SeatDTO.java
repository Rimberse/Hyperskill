package cinema.model.DTOs;

public class SeatDTO {
    private final int row;
    private final int column;
    private int price;

    public SeatDTO(int row, int column) {
        if (row > 0 && row < 10 && column > 0 && column < 10) {
            this.row = row;
            this.column = column;
        } else {
            throw new IllegalArgumentException("Row and columns values should be numerated from 1 to 9");
        }
    }

    public SeatDTO(int row, int column, int price) {
        this(row, column);
        this.price = price;
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
