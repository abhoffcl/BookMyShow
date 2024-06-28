package dev.Abhishek.BookMyShow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity(name="BMS_SHOW")
public class Show extends BaseModel{
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="movieId")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name="auditoriumId")
    private Auditorium auditorium;
    private Instant startTime;
    private Instant endTime;
    @OneToMany(mappedBy = "show",cascade = CascadeType.MERGE)
    private List<ShowSeat>showSeats;
}
