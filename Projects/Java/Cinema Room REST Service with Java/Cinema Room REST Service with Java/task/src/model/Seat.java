package model;

public class Seat {
    private final int row;
    private final int column;

    public Seat(int row, int column) {
        if (row > 0 && row < 10 && column > 0 && column < 10) {
            this.row = row;
            this.column = column;
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
}
