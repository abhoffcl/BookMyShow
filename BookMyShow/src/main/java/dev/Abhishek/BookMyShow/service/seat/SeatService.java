package dev.Abhishek.BookMyShow.service.seat;

import dev.Abhishek.BookMyShow.dto.SeatRequestDto;
import dev.Abhishek.BookMyShow.dto.SeatResponseDto;
import org.springframework.http.ResponseEntity;

public interface SeatService { 
    public SeatResponseDto createSeat(SeatRequestDto seatRequestDto);
    public Boolean deleteSeat(int id);
    public SeatResponseDto getSeat(int id);
    public SeatResponseDto updateSeat(int id,SeatRequestDto seatRequestDto);
}
