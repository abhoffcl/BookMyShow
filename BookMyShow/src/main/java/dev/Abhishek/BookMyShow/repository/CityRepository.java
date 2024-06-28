package dev.Abhishek.BookMyShow.repository;

import dev.Abhishek.BookMyShow.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CityRepository extends JpaRepository< City,Integer> {
    @Query("SELECT c FROM City c JOIN c.theatres t WHERE t.id = :theatreId")
    City findCityByTheatreId(@Param("theatreId") int theatreId);

}
