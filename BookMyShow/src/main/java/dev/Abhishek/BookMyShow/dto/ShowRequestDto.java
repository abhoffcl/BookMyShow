package dev.Abhishek.BookMyShow.dto;

import dev.Abhishek.BookMyShow.model.Show;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ShowRequestDto {
    private Integer movieId;
    private Integer auditoriumId;
    private Instant startTime;
    private Instant endTime;
}
