package dev.Abhishek.BookMyShow.controller;


import dev.Abhishek.BookMyShow.dto.SeatRequestDto;
import dev.Abhishek.BookMyShow.dto.SeatResponseDto;
import dev.Abhishek.BookMyShow.exception.InvalidInputException;
import dev.Abhishek.BookMyShow.service.seat.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @PostMapping("/")
    public ResponseEntity<SeatResponseDto> createSeat(@RequestBody SeatRequestDto seatRequestDto) {
        if (seatRequestDto.getRowz() < 1)
            throw new InvalidInputException("Enter valid row ");
        if (seatRequestDto.getColz() < 1)
            throw new InvalidInputException("Enter valid column");
        if (seatRequestDto.getAuditoriumId() < 1)
            throw new InvalidInputException("Enter valid auditorium id");

        return ResponseEntity.ok(seatService.createSeat(seatRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatResponseDto> updateSeat(@PathVariable("id") int id, @RequestBody SeatRequestDto seatRequestDto) {
        if (id < 1)
            throw new InvalidInputException("Enter valid Seat id ");
        return ResponseEntity.ok(seatService.updateSeat(id, seatRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatResponseDto> getSeat(@PathVariable("id") int id) {
        if (id < 1)
            throw new InvalidInputException("Enter valid Seat id ");
        return ResponseEntity.ok(seatService.getSeat(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSeat(@PathVariable("id") int id) {
        if (id < 1)
            throw new InvalidInputException("Enter valid Seat id ");
        return ResponseEntity.ok(seatService.deleteSeat(id));
    }
}