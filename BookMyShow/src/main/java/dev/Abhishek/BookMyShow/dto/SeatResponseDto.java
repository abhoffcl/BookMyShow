package dev.Abhishek.BookMyShow.dto;

import dev.Abhishek.BookMyShow.model.constant.SeatStatus;
import dev.Abhishek.BookMyShow.model.constant.SeatType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SeatResponseDto {
    private int id;
    private SeatType type;
    private SeatStatus status;
    private String number;



}
