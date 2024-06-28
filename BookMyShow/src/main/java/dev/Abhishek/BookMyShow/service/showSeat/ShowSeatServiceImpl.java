package dev.Abhishek.BookMyShow.service.showSeat;

import dev.Abhishek.BookMyShow.dto.ShowRequestDto;
import dev.Abhishek.BookMyShow.dto.ShowResponseDto;
import dev.Abhishek.BookMyShow.dto.ShowSeatRequestDto;
import dev.Abhishek.BookMyShow.dto.ShowSeatResponseDto;
import dev.Abhishek.BookMyShow.exception.*;
import dev.Abhishek.BookMyShow.model.*;
import dev.Abhishek.BookMyShow.repository.SeatRepository;
import dev.Abhishek.BookMyShow.repository.ShowRepository;
import dev.Abhishek.BookMyShow.repository.ShowSeatRepository;
import dev.Abhishek.BookMyShow.service.movie.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowSeatServiceImpl implements ShowSeatService {
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Override
    public ShowSeatResponseDto createShowSeat(ShowSeatRequestDto showSeatRequestDto){
        int showId=showSeatRequestDto.getShowId();
        int seatId=showSeatRequestDto.getSeatId();
        Show savedShow = showRepository.findById(showId).
                orElseThrow(()->new ShowNotFoundException("Show not found for id "+showId)) ;
        Seat savedSeat = seatRepository.findById(showSeatRequestDto.getSeatId()).
                orElseThrow(()->new SeatNotFoundException("Seat not found for id "+seatId)) ;
        ShowSeat showSeat= showSeatRequestDtoToEntity(showSeatRequestDto);
        showSeat.setShow(savedShow);
        showSeat.setSeat(savedSeat);
        showSeat.setPrice(showSeatRequestDto.getPrice());
        showSeat.setShowSeatStatus(showSeatRequestDto.getShowSeatStatus());
        return entityToShowSeatResponseDto(showSeatRepository.save(showSeat));
    }
    @Override
    public Boolean deleteShowSeat(int id) {
        ShowSeat showSeat = showSeatRepository.findById(id).
                orElseThrow(()->new ShowSeatNotFoundException("ShowSeat not found for id "+id));
        Show show = showSeat.getShow();
        if(show==null)
            throw new ShowNotFoundException("Show not found for ShowSeat id "+id);
        show.getShowSeats().remove(showSeat);
        showSeatRepository.delete(showSeat);
        showRepository.save(show);
        return true;
    }
    @Override
    public ShowSeatResponseDto getShowSeat(int id) {
        ShowSeat savedShowSeat= showSeatRepository.findById(id).
                orElseThrow(()->new ShowSeatNotFoundException("ShowSeat not found for id "+id));
        return entityToShowSeatResponseDto(savedShowSeat);
    }
    @Override
    public ShowSeatResponseDto updateShowSeat(int id, ShowSeatRequestDto showSeatRequestDto) {
        ShowSeat savedShowSeat = showSeatRepository.findById(id).
                orElseThrow(()->new ShowSeatNotFoundException("ShowSeat not found for id "+id));
        if(showSeatRequestDto.getShowId()!=null) {
            Show show = showRepository.findById(showSeatRequestDto.getShowId()).
                    orElseThrow(() -> new ShowNotFoundException("Show not found for id " + showSeatRequestDto.getShowId()));
            savedShowSeat.setShow(show);
        }
        if(showSeatRequestDto.getSeatId()!=null) {
            Seat seat = seatRepository.findById(showSeatRequestDto.getSeatId()).
                    orElseThrow(() -> new SeatNotFoundException("Seat not found for id " + showSeatRequestDto.getSeatId()));
            savedShowSeat.setSeat(seat);
        }
        if(showSeatRequestDto.getPrice()!=null)
            savedShowSeat.setPrice(showSeatRequestDto.getPrice());
        if(showSeatRequestDto.getShowSeatStatus()!=null)
            savedShowSeat.setShowSeatStatus(showSeatRequestDto.getShowSeatStatus());
        return entityToShowSeatResponseDto(showSeatRepository.save(savedShowSeat));
    }
    public  ShowSeat showSeatRequestDtoToEntity(ShowSeatRequestDto showSeatRequestDto){
        ShowSeat showSeat = new ShowSeat();
        showSeat.setShowSeatStatus(showSeatRequestDto.getShowSeatStatus());
        Show show = showRepository.findById(showSeatRequestDto.getShowId()).
                    orElseThrow(() -> new ShowNotFoundException("Show not found for id " + showSeatRequestDto.getShowId()));
        Seat seat = seatRepository.findById(showSeatRequestDto.getSeatId()).
                    orElseThrow(() -> new SeatNotFoundException("Seat not found for id " + showSeatRequestDto.getSeatId()));
        showSeat.setShow(show);
        showSeat.setSeat(seat);
        showSeat.setPrice(showSeatRequestDto.getPrice());
        return showSeat;
    }
    public ShowSeatResponseDto entityToShowSeatResponseDto(ShowSeat showSeat){
        ShowSeatResponseDto showSeatResponseDto = new ShowSeatResponseDto();
        showSeatResponseDto.setId(showSeat.getId());
        showSeatResponseDto.setShowId(showSeat.getShow().getId());
        showSeatResponseDto.setSeatId(showSeat.getSeat().getId());
        showSeatResponseDto.setShowSeatStatus(showSeat.getShowSeatStatus());
        showSeatResponseDto.setPrice(showSeat.getPrice());
        return showSeatResponseDto;
    }
}
