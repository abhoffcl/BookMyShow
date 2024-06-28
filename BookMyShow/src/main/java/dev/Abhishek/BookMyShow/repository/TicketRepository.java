package dev.Abhishek.BookMyShow.repository;

import dev.Abhishek.BookMyShow.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
