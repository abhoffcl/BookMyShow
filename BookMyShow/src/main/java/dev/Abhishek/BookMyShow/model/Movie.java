package dev.Abhishek.BookMyShow.model;

import dev.Abhishek.BookMyShow.model.constant.MovieFeatures;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel{
    private String name;
    @ManyToMany
    private List<Actor>actors;
    private String description;
    private Instant releaseDate;
    private String review;
    private double rating;
    @Enumerated(EnumType.STRING)
    private MovieFeatures movieFeatures;
}
