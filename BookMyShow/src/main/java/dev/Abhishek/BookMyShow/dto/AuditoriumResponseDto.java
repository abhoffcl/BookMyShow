package dev.Abhishek.BookMyShow.dto;

import dev.Abhishek.BookMyShow.model.Auditorium;
import dev.Abhishek.BookMyShow.model.Show;
import dev.Abhishek.BookMyShow.model.constant.AuditoriumFeatures;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AuditoriumResponseDto {
    private int id;
    private String name;
    private int capacity;
    private List<ShowResponseDto>shows;
    private List<SeatResponseDto> seats;
    private List<AuditoriumFeatures>auditoriumFeatures;


}
