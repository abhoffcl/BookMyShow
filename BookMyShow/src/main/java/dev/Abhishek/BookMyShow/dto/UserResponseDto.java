package dev.Abhishek.BookMyShow.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private Integer id;
    private String name;
    private List<TicketResponseDto> tickets;
}
