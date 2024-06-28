package dev.Abhishek.BookMyShow.repository;

import dev.Abhishek.BookMyShow.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface MovieRepository extends JpaRepository< Movie,Integer> {

}
