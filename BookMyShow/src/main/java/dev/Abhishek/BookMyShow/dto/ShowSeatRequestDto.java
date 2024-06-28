package dev.Abhishek.BookMyShow.dto;

import dev.Abhishek.BookMyShow.model.constant.ShowSeatStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowSeatRequestDto {
    private Integer showId;
    private Integer seatId;
    private Integer price;
    private ShowSeatStatus showSeatStatus;
}
