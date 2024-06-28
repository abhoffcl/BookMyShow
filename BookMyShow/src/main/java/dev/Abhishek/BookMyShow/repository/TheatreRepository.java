package dev.Abhishek.BookMyShow.repository;

import dev.Abhishek.BookMyShow.model.City;
import dev.Abhishek.BookMyShow.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TheatreRepository extends JpaRepository< Theatre,Integer> {
    @Query("SELECT t FROM Theatre t JOIN t.auditoriums a WHERE a.Id=:auditoriumId")
    Theatre findTheatreByAuditoriumId(@Param("auditoriumId")int auditoriumId);
}

