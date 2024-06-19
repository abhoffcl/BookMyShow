package dev.Abhishek.BookMyShow.model;

import dev.Abhishek.BookMyShow.model.constant.ShowSeatStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{
    @ManyToOne
    @JoinColumn(name = "showId")
    private Show show;
    @ManyToOne
    @JoinColumn(name="seatId")
    private Seat seat;
    private double price;
    @Enumerated(EnumType.STRING)
    private ShowSeatStatus showSeatStatus;
}
