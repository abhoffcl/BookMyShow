package dev.Abhishek.BookMyShow.controller;


import dev.Abhishek.BookMyShow.dto.TheatreRequestDto;
import dev.Abhishek.BookMyShow.dto.TheatreResponseDto;
import dev.Abhishek.BookMyShow.exception.InvalidInputException;
import dev.Abhishek.BookMyShow.service.theatre.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theatre")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;


    @PostMapping("/")
    public ResponseEntity<TheatreResponseDto> createTheatre(@RequestBody TheatreRequestDto theatreRequestDto){
        String theatreName=theatreRequestDto.getName();
        String theatreAddress =theatreRequestDto.getAddress();
        if(theatreName==null || theatreName.isEmpty() || theatreName.isBlank())
            throw new InvalidInputException("Enter valid theatre name");
        if(theatreAddress==null || theatreAddress.isEmpty() || theatreAddress.isBlank())
            throw new InvalidInputException("Enter valid theatre address");
        return ResponseEntity.ok(theatreService.createTheatre(theatreRequestDto));

    }
    @PutMapping("/{id}")
    public ResponseEntity<TheatreResponseDto> updateTheatre(@PathVariable("id")int id, @RequestBody TheatreRequestDto theatreRequestDto){
        if(id<1)
            throw new InvalidInputException("Enter valid Theatre id ");
        return ResponseEntity.ok(theatreService.updateTheatre(id,theatreRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TheatreResponseDto>getTheatre(@PathVariable("id")int id){
        if(id<1)
            throw new InvalidInputException("Enter valid Theatre id ");
        return ResponseEntity.ok(theatreService.getTheatre(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>deleteTheatre(@PathVariable("id")int id){
        if(id<1)
            throw new InvalidInputException("Enter valid Theatre id ");
        return ResponseEntity.ok(theatreService.deleteTheatre(id));
    }

}
