package dev.Abhishek.BookMyShow.service.movie;

import dev.Abhishek.BookMyShow.dto.MovieRequestDto;
import dev.Abhishek.BookMyShow.dto.MovieResponseDto;
import org.springframework.http.ResponseEntity;

public interface MovieService {
    public MovieResponseDto createMovie(MovieRequestDto movieRequestDto);
    public Boolean deleteMovie(int id);
    public MovieResponseDto getMovie(int id);
    public MovieResponseDto updateMovie(int id,MovieRequestDto movieRequestDto);
}
