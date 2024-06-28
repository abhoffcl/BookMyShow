package dev.Abhishek.BookMyShow.dto;

import dev.Abhishek.BookMyShow.model.constant.MovieFeature;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class MovieResponseDto {
    private int id;
    private String name;
    private String description;
    private MovieFeature movieFeature;
    private List<ActorResponseDto> actors;
}
