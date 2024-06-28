package dev.Abhishek.BookMyShow.service.city;

import dev.Abhishek.BookMyShow.dto.CityRequestDto;
import dev.Abhishek.BookMyShow.dto.CityResponseDto;
import org.springframework.http.ResponseEntity;

public interface CityService {
    public CityResponseDto createCity(CityRequestDto cityRequestDto);
    public Boolean deleteCity(int id );
    public CityResponseDto getCity(int id);
    public CityResponseDto updateCity(int id,CityRequestDto cityRequestDto);
}
