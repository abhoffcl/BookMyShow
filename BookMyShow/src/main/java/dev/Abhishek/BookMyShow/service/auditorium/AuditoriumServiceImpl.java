package dev.Abhishek.BookMyShow.service.auditorium;

import dev.Abhishek.BookMyShow.dto.*;
import dev.Abhishek.BookMyShow.exception.AuditoriumNotFoundException;
import dev.Abhishek.BookMyShow.exception.TheatreNotFoundException;
import dev.Abhishek.BookMyShow.model.*;
import dev.Abhishek.BookMyShow.repository.AuditoriumRepository;
import dev.Abhishek.BookMyShow.repository.TheatreRepository;
import dev.Abhishek.BookMyShow.service.seat.SeatService;
import dev.Abhishek.BookMyShow.service.seat.SeatServiceImpl;
import dev.Abhishek.BookMyShow.service.show.ShowService;
import dev.Abhishek.BookMyShow.service.show.ShowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditoriumServiceImpl implements AuditoriumService{
    @Autowired
    private ShowService showService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private AuditoriumRepository auditoriumRepository;
   

    @Override
    public AuditoriumResponseDto createAuditorium(AuditoriumRequestDto auditoriumRequestDto) {
        int theatreId=auditoriumRequestDto.getTheatreId() ;
        Theatre savedTheatre = theatreRepository.findById(theatreId).
                orElseThrow(()->new TheatreNotFoundException("Theatre not found for id "+theatreId)) ;
        List<Auditorium> auditoriums=savedTheatre.getAuditoriums()!=null?savedTheatre.getAuditoriums():new ArrayList<>();

        Auditorium auditorium= auditoriumRequestDtoToEntity(auditoriumRequestDto);
        Auditorium savedAuditorium= auditoriumRepository.save(auditorium);
        auditoriums.add(auditorium);
        savedTheatre.setAuditoriums(auditoriums);
        Theatre updatedTheatre =theatreRepository.save(savedTheatre);

        AuditoriumResponseDto auditoriumResponseDto =entityToAuditoriumResponseDto(savedAuditorium);

        return auditoriumResponseDto;
    }

    @Override
    public Boolean deleteAuditorium(int id) {
        Auditorium auditorium= auditoriumRepository.findById(id).
                orElseThrow(()->new AuditoriumNotFoundException("Auditorium not found for id "+id));
        Theatre theatre = theatreRepository.findTheatreByAuditoriumId(id);
        if(theatre==null)
            throw new TheatreNotFoundException("Theatre not found for auditorium id "+id);
        theatre.getAuditoriums().remove(auditorium);
        auditoriumRepository.delete(auditorium);
        theatreRepository.save(theatre);
        return true;
    }

    @Override
    public AuditoriumResponseDto getAuditorium(int id) {
        Auditorium savedAuditorium= auditoriumRepository.findById(id).
                orElseThrow(()->new AuditoriumNotFoundException("Auditorium not found for id "+id));
        AuditoriumResponseDto auditoriumResponseDto = entityToAuditoriumResponseDto(savedAuditorium);
        return auditoriumResponseDto;
    }

    @Override
    public  AuditoriumResponseDto updateAuditorium(int id, AuditoriumRequestDto auditoriumRequestDto) {
        Auditorium savedAuditorium= auditoriumRepository.findById(id).
                orElseThrow(()->new AuditoriumNotFoundException("Auditorium not found for id "+id));
        if(auditoriumRequestDto.getName()!=null)
            savedAuditorium.setName(auditoriumRequestDto.getName());
        if(auditoriumRequestDto.getCapacity()>0)
            savedAuditorium.setCapacity(auditoriumRequestDto.getCapacity());
        if(auditoriumRequestDto.getAuditoriumFeatures()!=null)
            savedAuditorium.setAuditoriumFeatures(auditoriumRequestDto.getAuditoriumFeatures());

        Auditorium updatedAuditorium=auditoriumRepository.save(savedAuditorium);
        AuditoriumResponseDto auditoriumResponseDto =entityToAuditoriumResponseDto(updatedAuditorium);
        return auditoriumResponseDto;
    }
    public  AuditoriumResponseDto entityToAuditoriumResponseDto(Auditorium auditorium){
        AuditoriumResponseDto auditoriumResponseDto = new AuditoriumResponseDto();
        auditoriumResponseDto.setId(auditorium.getId());
        auditoriumResponseDto.setName(auditorium.getName());
        auditoriumResponseDto.setAuditoriumFeatures(auditorium.getAuditoriumFeatures());
        auditoriumResponseDto.setCapacity(auditorium.getCapacity());
        List<SeatResponseDto>seatResponseDtos = new ArrayList<>();
        List<ShowResponseDto>showResponseDtos = new ArrayList<>();
        if(auditorium.getShows()!=null) {
            for (Seat seat: auditorium.getSeats())
                seatResponseDtos.add(((SeatServiceImpl)seatService).entityToSeatResponseDto(seat));
        }
        if(auditorium.getSeats()!=null){
            for(Show show:auditorium.getShows())
                showResponseDtos.add(((ShowServiceImpl)showService).entityToShowResponseDto(show));
        }
        auditoriumResponseDto.setSeats(seatResponseDtos);
        auditoriumResponseDto.setShows(showResponseDtos);
        return auditoriumResponseDto;

    }
    public  Auditorium auditoriumRequestDtoToEntity(AuditoriumRequestDto auditoriumRequestDto){
        Auditorium auditorium = new Auditorium();
        auditorium.setName(auditoriumRequestDto.getName());
        auditorium.setAuditoriumFeatures(auditoriumRequestDto.getAuditoriumFeatures());
        auditorium.setCapacity(auditoriumRequestDto.getCapacity());
        return auditorium;

    }
}
