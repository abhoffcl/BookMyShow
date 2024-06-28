package dev.Abhishek.BookMyShow.dto;

import dev.Abhishek.BookMyShow.model.Auditorium;
import dev.Abhishek.BookMyShow.model.Theatre;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TheatreResponseDto {
    private int id;
    private String name;
    private String address;
    private List<AuditoriumResponseDto> auditoriums;


}
