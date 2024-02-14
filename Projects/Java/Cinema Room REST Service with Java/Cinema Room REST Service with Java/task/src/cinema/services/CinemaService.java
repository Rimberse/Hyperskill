package cinema.services;

import cinema.model.DTOs.CinemaDTO;
import cinema.model.DTOs.SeatDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaService {
    public CinemaDTO getSeatsInfo() {
        int rows = 9;
        int columns = 9;

//        List<List<Seat>> seats = new ArrayList<>();
        List<SeatDTO> seats = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
//            List<Seat> seatsPerRow = new ArrayList<>();

            for (int column = 0; column < columns; column++) {
//                seatsPerRow.add(new Seat(row + 1, column + 1));
                seats.add(new SeatDTO(row + 1, column + 1));
            }

//            seats.add(seatsPerRow);
        }

        return new CinemaDTO(rows, columns, seats);
    }
}
