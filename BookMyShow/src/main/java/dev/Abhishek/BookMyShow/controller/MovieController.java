package dev.Abhishek.BookMyShow.controller;


import dev.Abhishek.BookMyShow.dto.MovieRequestDto;
import dev.Abhishek.BookMyShow.dto.MovieResponseDto;
import dev.Abhishek.BookMyShow.exception.InvalidInputException;
import dev.Abhishek.BookMyShow.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/")
    public ResponseEntity<MovieResponseDto> createMovie(@RequestBody MovieRequestDto movieRequestDto) {
        return ResponseEntity.ok(movieService.createMovie(movieRequestDto));

    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable("id") int id, @RequestBody MovieRequestDto movieRequestDto) {
        if (id < 1)
            throw new InvalidInputException("Enter valid Movie id ");
        return ResponseEntity.ok(movieService.updateMovie(id, movieRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovie(@PathVariable("id") int id) {
        if (id < 1)
            throw new InvalidInputException("Enter valid Movie id ");
        return ResponseEntity.ok(movieService.getMovie(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMovie(@PathVariable("id") int id) {
        if (id < 1)
            throw new InvalidInputException("Enter valid Movie id ");
        return ResponseEntity.ok(movieService.deleteMovie(id));
    }
}