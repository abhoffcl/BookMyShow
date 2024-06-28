package dev.Abhishek.BookMyShow.dto;

import dev.Abhishek.BookMyShow.model.City;
import dev.Abhishek.BookMyShow.model.Theatre;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CityResponseDto {
    private int id;
    private String name;
    private List<TheatreResponseDto> theatres;


}
