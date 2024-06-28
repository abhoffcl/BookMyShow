package dev.Abhishek.BookMyShow.controller;

import dev.Abhishek.BookMyShow.dto.ActorRequestDto;
import dev.Abhishek.BookMyShow.dto.ActorResponseDto;
import dev.Abhishek.BookMyShow.exception.InvalidInputException;
import dev.Abhishek.BookMyShow.service.actor.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actor")
public class ActorController {
    @Autowired
    private ActorService actorService;
    @PostMapping("/")
    public ResponseEntity<ActorResponseDto> createActor(@RequestBody ActorRequestDto actorRequestDto){
        return ResponseEntity.ok(actorService.createActor(actorRequestDto));

    }
    @PutMapping("/{id}")
    public ResponseEntity<ActorResponseDto> updateActor(@PathVariable("id")int id, @RequestBody ActorRequestDto actorRequestDto){
        if(id<1)
            throw new InvalidInputException("Enter valid actor id ");
        return ResponseEntity.ok(actorService.updateActor(id,actorRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ActorResponseDto>getActor(@PathVariable("id")int id){
        if(id<1)
            throw new InvalidInputException("Enter valid actor id ");
        return ResponseEntity.ok(actorService.getActor(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>deleteActor(@PathVariable("id")int id){
        if(id<1)
            throw new InvalidInputException("Enter valid actor id ");
        return ResponseEntity.ok(actorService.deleteActor(id));
    }


}
