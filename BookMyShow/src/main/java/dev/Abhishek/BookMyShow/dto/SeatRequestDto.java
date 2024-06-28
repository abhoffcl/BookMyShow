package dev.Abhishek.BookMyShow.dto;

import dev.Abhishek.BookMyShow.model.constant.SeatStatus;
import dev.Abhishek.BookMyShow.model.constant.SeatType;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatRequestDto {
    private Integer rowz;
    private Integer colz;
    private String seatnumber;
    private SeatType seatType;
    private SeatStatus seatStatus;
    private Integer auditoriumId;
}
