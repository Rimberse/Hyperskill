package cinema.model.DTOs;

import cinema.model.Seat;

import java.util.List;

public class CinemaDTO {
    private int rows;
    private int columns;
//    private List<List<Seat>> seats;
    private List<Seat> seats;

//    public CinemaDTO(int rows, int columns, List<List<Seat>> seats) {
    public CinemaDTO(int rows, int columns, List<Seat> seats) {
        this.rows = rows;
        this.columns = columns;
        this.seats = seats;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

//    public List<List<Seat>> getSeats() {
    public List<Seat> getSeats() {
        return seats;
    }
}
