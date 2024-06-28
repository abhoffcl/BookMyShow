package dev.Abhishek.BookMyShow.repository;

import dev.Abhishek.BookMyShow.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShowRepository extends JpaRepository< Show,Integer> {
}
