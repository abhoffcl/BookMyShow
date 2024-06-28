package dev.Abhishek.BookMyShow.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.MERGE)
    private List<Ticket>tickets;

}
