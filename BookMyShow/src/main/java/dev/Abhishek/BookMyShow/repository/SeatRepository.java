package dev.Abhishek.BookMyShow.repository;

import dev.Abhishek.BookMyShow.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat,Integer > {
}
