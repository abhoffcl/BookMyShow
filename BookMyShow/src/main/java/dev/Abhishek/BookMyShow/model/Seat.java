package dev.Abhishek.BookMyShow.model;

import dev.Abhishek.BookMyShow.model.constant.SeatStatus;
import dev.Abhishek.BookMyShow.model.constant.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private String seatNumber;
    private int rowz;
    private int colz;
    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;
}
