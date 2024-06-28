package dev.Abhishek.BookMyShow.dto;

import dev.Abhishek.BookMyShow.model.constant.ShowSeatStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowSeatResponseDto {
    private int id;
    private int showId;
    private int seatId;
    private double price;
    private ShowSeatStatus showSeatStatus;
}
