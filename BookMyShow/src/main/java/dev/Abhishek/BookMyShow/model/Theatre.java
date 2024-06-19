package dev.Abhishek.BookMyShow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{
    private String name;
    @OneToMany
    @JoinColumn(name = "theatreId")
    private List<Auditorium> auditoriums;
    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

}
