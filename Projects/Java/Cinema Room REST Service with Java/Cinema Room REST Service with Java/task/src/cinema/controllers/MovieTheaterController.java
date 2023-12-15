package cinema.controllers;

import cinema.model.DTOs.CinemaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cinema.services.CinemaService;

@RestController
@RequestMapping("/seats")
public class MovieTheaterController {
    private final CinemaService cinemaService;

    @Autowired
    public MovieTheaterController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping
    public CinemaDTO getSeatsInfo() {
        System.out.println(cinemaService.getSeatsInfo());
        return cinemaService.getSeatsInfo();
    }
}
