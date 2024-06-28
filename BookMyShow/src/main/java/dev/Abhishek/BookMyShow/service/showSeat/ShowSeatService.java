package dev.Abhishek.BookMyShow.service.showSeat;

import dev.Abhishek.BookMyShow.dto.ShowSeatRequestDto;
import dev.Abhishek.BookMyShow.dto.ShowSeatResponseDto;
import org.springframework.http.ResponseEntity;

    public interface ShowSeatService {
        public ShowSeatResponseDto createShowSeat(ShowSeatRequestDto ShowSeatRequestDto);
        public Boolean deleteShowSeat(int id);
        public ShowSeatResponseDto getShowSeat(int id);
        public ShowSeatResponseDto updateShowSeat(int id,ShowSeatRequestDto ShowSeatRequestDto);
    }

