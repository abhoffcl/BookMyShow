package dev.Abhishek.BookMyShow.controller;


import dev.Abhishek.BookMyShow.dto.ShowSeatRequestDto;
import dev.Abhishek.BookMyShow.dto.ShowSeatResponseDto;
import dev.Abhishek.BookMyShow.exception.InvalidInputException;
import dev.Abhishek.BookMyShow.service.showSeat.ShowSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/showSeat")
public class ShowSeatController {
    @Autowired
    private ShowSeatService ShowSeatService;

    @PostMapping("/")
    public ResponseEntity<ShowSeatResponseDto> createShowSeat(@RequestBody ShowSeatRequestDto showSeatRequestDto) {
        if(showSeatRequestDto.getSeatId()==null || showSeatRequestDto.getSeatId()<1)
            throw new InvalidInputException("Enter valid seat id for showSeat");
        if(showSeatRequestDto.getShowId()==null || showSeatRequestDto.getShowId()<1)
            throw new InvalidInputException("Enter valid  show id for showSeat");
        if(showSeatRequestDto.getPrice()<1)
            throw new InvalidInputException("Enter valid price for showSeat");
        return ResponseEntity.ok(ShowSeatService.createShowSeat(showSeatRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowSeatResponseDto> updateShowSeat(@PathVariable("id") int id, @RequestBody ShowSeatRequestDto showSeatRequestDto) {
        if (id < 1)
            throw new InvalidInputException("Enter valid ShowSeat id ");
        return ResponseEntity.ok(ShowSeatService.updateShowSeat(id, showSeatRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowSeatResponseDto> getShowSeat(@PathVariable("id") int id) {
        if (id < 1)
            throw new InvalidInputException("Enter valid ShowSeat id ");
        return ResponseEntity.ok(ShowSeatService.getShowSeat(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteShowSeat(@PathVariable("id") int id) {
        if (id < 1)
            throw new InvalidInputException("Enter valid ShowSeat id ");
        return ResponseEntity.ok(ShowSeatService.deleteShowSeat(id));
    }
}