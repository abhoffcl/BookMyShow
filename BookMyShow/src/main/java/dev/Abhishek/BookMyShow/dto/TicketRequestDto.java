package dev.Abhishek.BookMyShow.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketRequestDto {
    private Integer userId;
    private List<Integer>showSeatId;
}
