package dev.Abhishek.BookMyShow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private UUID id;
    @Column(name="CREATED_AT")
    private Instant createdAt;
    @Column(name="UPDATED_AT")
    private Instant updatedAt;
    @Column(name="CREATED_BY")
    private String createdBy;
    @Column(name="UPDATED_BY")
    private String updatedBy;
}
