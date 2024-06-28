package dev.Abhishek.BookMyShow.service.theatre;

import dev.Abhishek.BookMyShow.dto.AuditoriumResponseDto;
import dev.Abhishek.BookMyShow.dto.TheatreResponseDto;
import dev.Abhishek.BookMyShow.dto.TheatreRequestDto;
import dev.Abhishek.BookMyShow.dto.TheatreResponseDto;
import dev.Abhishek.BookMyShow.exception.CityNotFoundException;
import dev.Abhishek.BookMyShow.exception.TheatreNotFoundException;
import dev.Abhishek.BookMyShow.model.Auditorium;
import dev.Abhishek.BookMyShow.model.City;
import dev.Abhishek.BookMyShow.model.Theatre;
import dev.Abhishek.BookMyShow.repository.CityRepository;
import dev.Abhishek.BookMyShow.repository.TheatreRepository;
import dev.Abhishek.BookMyShow.service.auditorium.AuditoriumService;
import dev.Abhishek.BookMyShow.service.auditorium.AuditoriumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreServiceImpl implements TheatreService {
    @Autowired
    private AuditoriumService auditoriumService;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private CityRepository cityRepository;


    @Override
    public TheatreResponseDto createTheatre(TheatreRequestDto theatreRequestDto) {
        int cityId=theatreRequestDto.getCityId() ;
        City savedCity = cityRepository.findById(cityId).
                orElseThrow(()->new CityNotFoundException("City not found for id "+cityId)) ;
        List<Theatre>theatres=savedCity.getTheatres()!=null?savedCity.getTheatres():new ArrayList<>();

        Theatre theatre=theatreRequestDtoToEntity(theatreRequestDto);
        Theatre savedTheatre= theatreRepository.save(theatre);
        theatres.add(theatre);
        savedCity.setTheatres(theatres);
        City updatedCity =cityRepository.save(savedCity);

        TheatreResponseDto theatreResponseDto = entityToTheatreResponseDto(savedTheatre);

        return theatreResponseDto;
    }

    @Override
    public Boolean deleteTheatre(int id) {

        Theatre theatre= theatreRepository.findById(id).
                orElseThrow(()->new TheatreNotFoundException("Theatre not found for id "+id));
        City city = cityRepository.findCityByTheatreId(id);
        if(city==null)
            throw new CityNotFoundException("city not found for theater id "+id);
        city.getTheatres().remove(theatre);
        theatreRepository.delete(theatre);
        cityRepository.save(city);
        return true;
    }
    @Override
    public TheatreResponseDto getTheatre(int id) {
        Theatre savedTheatre= theatreRepository.findById(id).
                orElseThrow(()->new TheatreNotFoundException("Theatre not found for id "+id));
        return entityToTheatreResponseDto(savedTheatre);
    }

    @Override
    public TheatreResponseDto updateTheatre(int id,TheatreRequestDto theatreRequestDto) {
       Theatre savedTheatre= theatreRepository.findById(id).
               orElseThrow(()->new TheatreNotFoundException("Theatre not found for id "+id));
       if(theatreRequestDto.getName()!=null)
        savedTheatre.setName(theatreRequestDto.getName());
       if(theatreRequestDto.getAddress()!=null)
           savedTheatre.setAddress(theatreRequestDto.getAddress());
        return  entityToTheatreResponseDto(theatreRepository.save(savedTheatre));
    }
    public  TheatreResponseDto entityToTheatreResponseDto(Theatre theatre){
        TheatreResponseDto theatreResponseDto = new TheatreResponseDto();
        theatreResponseDto.setId(theatre.getId());
        theatreResponseDto.setName(theatre.getName());
        theatreResponseDto.setAddress(theatre.getAddress());
        List<AuditoriumResponseDto>auditoriumResponseDtos=new ArrayList<>();
        if(theatre.getAuditoriums()!=null) {
            for (Auditorium auditorium : theatre.getAuditoriums()){
                auditoriumResponseDtos.add(((AuditoriumServiceImpl)auditoriumService).entityToAuditoriumResponseDto(auditorium));
            }
        }
        theatreResponseDto.setAuditoriums(auditoriumResponseDtos);
        return theatreResponseDto;
    }
    public  Theatre theatreRequestDtoToEntity(TheatreRequestDto theatreRequestDto){
        Theatre theatre = new Theatre();
        theatre.setName(theatreRequestDto.getName());
        theatre.setAddress(theatreRequestDto.getAddress());
        return theatre;
    }
}
