package dev.Abhishek.BookMyShow.service.seat;

import dev.Abhishek.BookMyShow.dto.*;
import dev.Abhishek.BookMyShow.exception.AuditoriumNotFoundException;
import dev.Abhishek.BookMyShow.exception.SeatNotFoundException;
import dev.Abhishek.BookMyShow.model.*;
import dev.Abhishek.BookMyShow.repository.AuditoriumRepository;
import dev.Abhishek.BookMyShow.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private AuditoriumRepository auditoriumRepository;
    @Override
    public SeatResponseDto createSeat(SeatRequestDto seatRequestDto) {
        int auditoriumId=seatRequestDto.getAuditoriumId() ;
        Auditorium savedAuditorium = auditoriumRepository.findById(auditoriumId).
                orElseThrow(()->new AuditoriumNotFoundException("Auditorium not found for id "+auditoriumId)) ;
        List<Seat> seats=savedAuditorium.getSeats()!=null?savedAuditorium.getSeats():new ArrayList<>();
        Seat seat= seatRequestDtoToEntity(seatRequestDto);
        Seat savedSeat= seatRepository.save(seat);
        seats.add(seat);
        savedAuditorium.setSeats(seats);
        auditoriumRepository.save(savedAuditorium);
        return entityToSeatResponseDto(savedSeat);
    }
    @Override
    public Boolean deleteSeat(int id) {
        Seat seat= seatRepository.findById(id).
                orElseThrow(()->new SeatNotFoundException("Seat not found for id "+id));
        Auditorium auditorium = auditoriumRepository.findAuditoriumBySeatId(id);
        if(auditorium==null)
            throw new AuditoriumNotFoundException("Auditorium not found for seat id "+id);
        auditorium.getSeats().remove(seat);
        seatRepository.delete(seat);
        auditoriumRepository.save(auditorium);
        return true;
    }
    @Override
    public SeatResponseDto getSeat(int id) {
        Seat savedSeat= seatRepository.findById(id).
                orElseThrow(()->new SeatNotFoundException("Seat not found for id "+id));
        return entityToSeatResponseDto(savedSeat);
    }
    @Override
    public SeatResponseDto updateSeat(int id,SeatRequestDto seatRequestDto) {
        Seat savedSeat= seatRepository.findById(id).
                orElseThrow(()->new SeatNotFoundException("Seat not found for id "+id));
        if(seatRequestDto.getSeatType()!=null)
            savedSeat.setSeatType(seatRequestDto.getSeatType());
        if(seatRequestDto.getSeatStatus()!=null)
            savedSeat.setSeatStatus(seatRequestDto.getSeatStatus());
        if(seatRequestDto.getSeatnumber()!=null)
            savedSeat.setSeatNumber(seatRequestDto.getSeatnumber());
        Seat updatedSeat=seatRepository.save(savedSeat);
        return entityToSeatResponseDto(updatedSeat);
    }
    public Seat seatRequestDtoToEntity(SeatRequestDto seatRequestDto){
        Seat seat = new Seat();
        seat.setRowz(seatRequestDto.getRowz());
        seat.setColz(seatRequestDto.getColz());
        seat.setSeatType(seatRequestDto.getSeatType());
        seat.setSeatNumber(seatRequestDto.getSeatnumber());
        seat.setSeatStatus(seatRequestDto.getSeatStatus());
        return seat;
    }
    public SeatResponseDto entityToSeatResponseDto(Seat seat){
        SeatResponseDto seatResponseDto = new SeatResponseDto();
        seatResponseDto.setId(seat.getId());
        seatResponseDto.setNumber(seat.getSeatNumber());
        seatResponseDto.setStatus(seat.getSeatStatus());
        seatResponseDto.setType(seat.getSeatType());
        return seatResponseDto;
    }
}
