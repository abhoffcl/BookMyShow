package dev.Abhishek.BookMyShow.repository;

import dev.Abhishek.BookMyShow.model.ShowSeat;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Integer > {
}
