package model;

public class Seat {
    private final int row;
    private final int column;
    private boolean isBooked;

    public Seat(int row, int column) {
        if (row > 0 && row < 10 && column > 0 && column < 10) {
            this.row = row;
            this.column = column;
            this.isBooked = false;
        } else {
            throw new IllegalArgumentException("Row and columns values should be numerated from 1 to 9");
        }
    }

    public Seat(int row, int column, boolean isBooked) {
        this(row, column);
        this.isBooked = true;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        isBooked = true;
    }

    public void cancelBooking() {
        isBooked = false;
    }
}
