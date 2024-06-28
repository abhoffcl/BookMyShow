package dev.Abhishek.BookMyShow.dto;

import dev.Abhishek.BookMyShow.model.Show;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ShowResponseDto {
    private int showId;
    private MovieResponseDto movie;
    private int auditoriumId;
    private Instant startTime;
    private Instant endTime;
    private List<ShowSeatResponseDto> showSeats;

}
