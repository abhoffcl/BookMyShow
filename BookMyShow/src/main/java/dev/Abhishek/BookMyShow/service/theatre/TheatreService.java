package dev.Abhishek.BookMyShow.service.theatre;

import dev.Abhishek.BookMyShow.dto.TheatreRequestDto;
import dev.Abhishek.BookMyShow.dto.TheatreResponseDto;
import org.springframework.http.ResponseEntity;

public interface TheatreService {
    public TheatreResponseDto createTheatre(TheatreRequestDto theatreRequestDto);
    public Boolean deleteTheatre(int id);
    public TheatreResponseDto getTheatre(int id);
    public TheatreResponseDto updateTheatre(int id ,TheatreRequestDto theatreRequestDto);
}
