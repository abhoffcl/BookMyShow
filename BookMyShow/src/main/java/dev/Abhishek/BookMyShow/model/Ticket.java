package dev.Abhishek.BookMyShow.model;

import dev.Abhishek.BookMyShow.model.constant.TicketStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel{
    @OneToMany
    @JoinColumn(name="ticketId")
    private List<ShowSeat> showSeat;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private Instant bookingTime;
    private double totalAmount;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
}
