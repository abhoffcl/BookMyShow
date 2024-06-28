package dev.Abhishek.BookMyShow.service.city;

import dev.Abhishek.BookMyShow.dto.ActorResponseDto;
import dev.Abhishek.BookMyShow.dto.CityRequestDto;
import dev.Abhishek.BookMyShow.dto.CityResponseDto;
import dev.Abhishek.BookMyShow.dto.TheatreResponseDto;
import dev.Abhishek.BookMyShow.exception.ActorNotFoundException;
import dev.Abhishek.BookMyShow.exception.CityNotFoundException;
import dev.Abhishek.BookMyShow.model.Actor;
import dev.Abhishek.BookMyShow.model.City;
import dev.Abhishek.BookMyShow.model.Theatre;
import dev.Abhishek.BookMyShow.repository.CityRepository;
import dev.Abhishek.BookMyShow.service.theatre.TheatreService;
import dev.Abhishek.BookMyShow.service.theatre.TheatreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService{
    @Autowired
    private TheatreService theatreService;
    @Autowired
    private CityRepository cityRepository;
    @Override
    public CityResponseDto createCity(CityRequestDto cityRequestDto) {
        City city = new City();
        city.setName(cityRequestDto.getName());
        City savedCity = cityRepository.save(city);
        CityResponseDto cityResponseDto = entityToCityResponseDto(savedCity);
        return cityResponseDto;
    }

    @Override
    public Boolean deleteCity(int id) {
        City city= cityRepository.findById(id).orElseThrow(()->new CityNotFoundException("City not found for id "+id));
        cityRepository.delete(city);
        return true;
    }

    @Override
    public CityResponseDto getCity(int id) {
        City savedCity= cityRepository.findById(id).orElseThrow(()->new CityNotFoundException("City not found for id "+id));
         CityResponseDto cityResponseDto = entityToCityResponseDto(savedCity);
        return cityResponseDto;
    }

    @Override
    public CityResponseDto updateCity(int id,CityRequestDto cityRequestDto) {
        City savedCity= cityRepository.findById(id).orElseThrow(()->new CityNotFoundException("City not found for id "+id));
        savedCity.setName(cityRequestDto.getName());
        City updateCity=cityRepository.save(savedCity);
        CityResponseDto cityResponseDto = new CityResponseDto();
        cityResponseDto.setId(updateCity.getId());
        cityResponseDto.setName(updateCity.getName());
        return cityResponseDto;
    }
    public  CityResponseDto entityToCityResponseDto(City city){
        CityResponseDto cityResponseDto = new CityResponseDto();
        cityResponseDto.setId(city.getId());
        cityResponseDto.setName(city.getName());

        List<Theatre> theatres = city.getTheatres();
        List<TheatreResponseDto>theatreResponseDtos =new ArrayList<>();
        if(theatres!=null) {
            for (Theatre theatre : theatres) {
                theatreResponseDtos.add(((TheatreServiceImpl)theatreService).entityToTheatreResponseDto(theatre));
            }
        }
        cityResponseDto.setTheatres(theatreResponseDtos);
        return cityResponseDto;
    }
    public static City CityRequestDtoToEntity(CityRequestDto cityRequestDto){
        City city = new City();
        city.setName(cityRequestDto.getName());
        return city;
    }

}
