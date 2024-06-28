package dev.Abhishek.BookMyShow.repository;

import dev.Abhishek.BookMyShow.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActorRepository extends JpaRepository<Actor,Integer> {
}
