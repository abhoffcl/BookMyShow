package dev.Abhishek.BookMyShow.repository;

import dev.Abhishek.BookMyShow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository< User,Integer> {
    User findUserByEmail(String email);
    @Query("SELECT u FROM User u JOIN u.tickets t WHERE t.id = :ticketId")
    User findUserByTicketId(@Param("ticketId")int ticketId);

}
