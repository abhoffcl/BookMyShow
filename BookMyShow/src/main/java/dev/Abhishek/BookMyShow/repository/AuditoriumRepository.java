package dev.Abhishek.BookMyShow.repository;

import dev.Abhishek.BookMyShow.model.Auditorium;
import dev.Abhishek.BookMyShow.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AuditoriumRepository extends JpaRepository< Auditorium,Integer> {
    @Query("SELECT a FROM Auditorium a JOIN a.seats s WHERE s.id= :seatId")
    Auditorium findAuditoriumBySeatId(@Param("seatId")int seatId);

}
