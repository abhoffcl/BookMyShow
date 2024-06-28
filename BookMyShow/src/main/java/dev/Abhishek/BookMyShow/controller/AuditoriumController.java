package dev.Abhishek.BookMyShow.controller;

import dev.Abhishek.BookMyShow.dto.AuditoriumRequestDto;
import dev.Abhishek.BookMyShow.dto.AuditoriumResponseDto;
import dev.Abhishek.BookMyShow.exception.InvalidInputException;
import dev.Abhishek.BookMyShow.service.auditorium.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auditorium")
public class AuditoriumController {
    @Autowired
    private AuditoriumService auditoriumService;


    @PostMapping("/")
    public ResponseEntity<AuditoriumResponseDto> createAuditorium(@RequestBody AuditoriumRequestDto auditoriumRequestDto){
        String auditoriumName=auditoriumRequestDto.getName();
        if(auditoriumName==null || auditoriumName.isEmpty() || auditoriumName.isBlank())
            throw new InvalidInputException("Enter valid Auditorium name");
        return ResponseEntity.ok(auditoriumService.createAuditorium(auditoriumRequestDto));

    }
    @PutMapping("/{id}")
    public ResponseEntity<AuditoriumResponseDto> updateAuditorium(@PathVariable("id")int id, @RequestBody AuditoriumRequestDto auditoriumRequestDto){
        if(id<1)
            throw new InvalidInputException("Enter valid Auditorium id ");
        return ResponseEntity.ok(auditoriumService.updateAuditorium(id,auditoriumRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AuditoriumResponseDto>getAuditorium(@PathVariable("id")int id){
        if(id<1)
            throw new InvalidInputException("Enter valid Auditorium id ");
        return ResponseEntity.ok(auditoriumService.getAuditorium(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>deleteAuditorium(@PathVariable("id")int id){
        if(id<1)
            throw new InvalidInputException("Enter valid Auditorium id ");
        return ResponseEntity.ok(auditoriumService.deleteAuditorium(id));
    }

}
