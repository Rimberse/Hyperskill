package services;

import model.DTOs.CinemaDTO;
import model.Seat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaService {
    public CinemaDTO getSeatsInfo() {
        int rows = 9;
        int columns = 9;

        List<List<Seat>> seats = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            List<Seat> seatsPerRow = new ArrayList<>();

            for (int column = 0; column < columns; column++) {
                seatsPerRow.add(new Seat(row, column));
            }

            seats.add(seatsPerRow);
        }

        return new CinemaDTO(rows, columns, seats);
    }
}
