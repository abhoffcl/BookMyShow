package dev.Abhishek.BookMyShow.service.show;

import dev.Abhishek.BookMyShow.dto.ShowRequestDto;
import dev.Abhishek.BookMyShow.dto.ShowResponseDto;
import org.springframework.http.ResponseEntity;

public interface ShowService {
    public ShowResponseDto createShow(ShowRequestDto showRequestDto);
    public Boolean deleteShow(int id);
    public ShowResponseDto getShow(int id);
    public ShowResponseDto updateShow(int id,ShowRequestDto showRequestDto);
}
