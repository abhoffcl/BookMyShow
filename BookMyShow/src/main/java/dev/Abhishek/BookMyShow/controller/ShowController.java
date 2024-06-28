package dev.Abhishek.BookMyShow.controller;

import dev.Abhishek.BookMyShow.dto.ShowRequestDto;
import dev.Abhishek.BookMyShow.dto.ShowResponseDto;
import dev.Abhishek.BookMyShow.exception.InvalidInputException;
import dev.Abhishek.BookMyShow.service.show.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/show")
public class ShowController {
        @Autowired
        private ShowService ShowService;

        @PostMapping("/")
        public ResponseEntity<ShowResponseDto> createShow(@RequestBody ShowRequestDto showRequestDto) {
           if(showRequestDto.getMovieId()<1)
               throw new InvalidInputException("Enter valid movie id");
           if(showRequestDto.getAuditoriumId()<1)
               throw new InvalidInputException("Enter valid auditorium id");
           if(showRequestDto.getStartTime().isBefore( Instant.now()))
               throw new InvalidInputException("Enter valid show start time ");
            if(showRequestDto.getEndTime().isBefore(showRequestDto.getStartTime()))
                throw new InvalidInputException("Enter valid show end time ");
            return ResponseEntity.ok(ShowService.createShow(showRequestDto));
        }
        @PutMapping("/{id}")
        public ResponseEntity<ShowResponseDto> updateShow(@PathVariable("id") int id, @RequestBody ShowRequestDto showRequestDto) {
            if (id < 1)
                throw new InvalidInputException("Enter valid Show id ");
            return ResponseEntity.ok(ShowService.updateShow(id, showRequestDto));
        }
        @GetMapping("/{id}")
        public ResponseEntity<ShowResponseDto> getShow(@PathVariable("id") int id) {
            if (id < 1)
                throw new InvalidInputException("Enter valid Show id ");
            return ResponseEntity.ok(ShowService.getShow(id));
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Boolean> deleteShow(@PathVariable("id") int id) {
            if (id < 1)
                throw new InvalidInputException("Enter valid Show id ");
            return ResponseEntity.ok(ShowService.deleteShow(id));
        }
}

