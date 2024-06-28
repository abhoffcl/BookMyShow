package dev.Abhishek.BookMyShow.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class City extends BaseModel{
    private String name;
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name="cityId")
    private List<Theatre> theatres;
}
