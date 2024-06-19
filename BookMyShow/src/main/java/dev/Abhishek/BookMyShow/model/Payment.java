package dev.Abhishek.BookMyShow.model;

import dev.Abhishek.BookMyShow.model.constant.PaymentMode;
import dev.Abhishek.BookMyShow.model.constant.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {
    private Instant paymentTime;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String referenceId;
    private double amount;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    @ManyToOne
    @JoinColumn(name = "ticketId")
    private Ticket ticket;
}
