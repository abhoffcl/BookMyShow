package dev.Abhishek.BookMyShow.model;

import dev.Abhishek.BookMyShow.model.constant.AuditoriumFeatures;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Auditorium extends BaseModel{
    private String name;
    private int capacity;
    @OneToMany
    @JoinColumn(name = "auditoriumId")
    private List<Seat> seats;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<AuditoriumFeatures>auditoriumFeatures;
    @OneToMany(mappedBy = "auditorium")
    private List<Show>shows;


}
