package dev.Abhishek.BookMyShow.controller;

import dev.Abhishek.BookMyShow.dto.CityRequestDto;
import dev.Abhishek.BookMyShow.dto.CityResponseDto;
import dev.Abhishek.BookMyShow.exception.InvalidInputException;
import dev.Abhishek.BookMyShow.service.city.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping("/")
    public ResponseEntity<CityResponseDto> createActor(@RequestBody CityRequestDto cityRequestDto){
        return ResponseEntity.ok(cityService.createCity(cityRequestDto));

    }
    @PutMapping("/{id}")
    public ResponseEntity<CityResponseDto> updateCity(@PathVariable("id")int id, @RequestBody CityRequestDto CityRequestDto){
        if(id<1)
            throw new InvalidInputException("Enter valid City id ");
        return ResponseEntity.ok(cityService.updateCity(id,CityRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CityResponseDto>getCity(@PathVariable("id")int id){
        if(id<1)
            throw new InvalidInputException("Enter valid City id ");
        return ResponseEntity.ok(cityService.getCity(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>deleteCity(@PathVariable("id")int id){
        if(id<1)
            throw new InvalidInputException("Enter valid City id ");
        return ResponseEntity.ok(cityService.deleteCity(id));
    }


}
