package dev.Abhishek.BookMyShow.dto;

import dev.Abhishek.BookMyShow.model.ShowSeat;
import dev.Abhishek.BookMyShow.model.User;
import dev.Abhishek.BookMyShow.model.constant.TicketStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class TicketResponseDto {
    private Integer ticketId;
    private List<Integer> showSeatId;
    private Integer userId;
    private Instant bookingTime;
    private Double totalAmount;
    private TicketStatus ticketStatus;
    private Instant showTime;
}
