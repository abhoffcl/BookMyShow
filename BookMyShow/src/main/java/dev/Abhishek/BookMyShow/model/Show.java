package dev.Abhishek.BookMyShow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity(name="BMS_SHOW")
public class Show extends BaseModel{
    @ManyToOne
    @JoinColumn(name="movieId")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name="auditoriumId")
    private Auditorium auditorium;
    private Instant startTime;
    private Instant endTime;
    @OneToMany(mappedBy = "show")
    private List<ShowSeat>showSeats;
}
