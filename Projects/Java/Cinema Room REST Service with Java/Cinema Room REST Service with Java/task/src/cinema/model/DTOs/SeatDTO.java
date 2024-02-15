package cinema.model.DTOs;

public class SeatDTO {
    private final int row;
    private final int column;
    private int price;

    public SeatDTO(int row, int column) {
        if (row > 0 && row < 10 && column > 0 && column < 10) {
            this.row = row;
            this.column = column;

            if (row < 5) {
                this.price = 10;
            } else {
                this.price = 8;
            }
        } else {
            throw new IllegalArgumentException("Row and columns values should be numerated from 1 to 9");
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

    public void setPrice(int price) {
        this.price = price;
    }
}
