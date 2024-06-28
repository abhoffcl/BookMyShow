package dev.Abhishek.BookMyShow.dto;

import dev.Abhishek.BookMyShow.model.Actor;
import dev.Abhishek.BookMyShow.model.constant.MovieFeature;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class MovieRequestDto {
    private String name;
    private List<Integer> actorIds;
    private String descpription;
    private Instant releaseDate;
    private MovieFeature feature;
}
