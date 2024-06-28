package dev.Abhishek.BookMyShow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{
    private String name;
    private String address;
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "theatreId")
    private List<Auditorium> auditoriums;

}
