package dev.Abhishek.BookMyShow.service.ticket;

import dev.Abhishek.BookMyShow.dto.TicketRequestDto;
import dev.Abhishek.BookMyShow.dto.TicketResponseDto;
import dev.Abhishek.BookMyShow.exception.*;
import dev.Abhishek.BookMyShow.model.ShowSeat;
import dev.Abhishek.BookMyShow.model.Ticket;
import dev.Abhishek.BookMyShow.model.User;
import dev.Abhishek.BookMyShow.model.constant.ShowSeatStatus;
import dev.Abhishek.BookMyShow.model.constant.TicketStatus;
import dev.Abhishek.BookMyShow.repository.ShowSeatRepository;
import dev.Abhishek.BookMyShow.repository.TicketRepository;
import dev.Abhishek.BookMyShow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Override
    public TicketResponseDto createTicket(TicketRequestDto ticketRequestDto) {
        List<ShowSeat>showSeats=getShowSeatById(ticketRequestDto.getShowSeatId());
        checkAndLockSeats(showSeats);
        Ticket ticket = ticketRequestDtoToEntity(ticketRequestDto);
        if(makePayment()) {
            ticket.setTicketStatus(TicketStatus.CONFIRMED);
            for(ShowSeat showSeat:showSeats){
                showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
            }
        }
        else {
            ticket.setTicketStatus(TicketStatus.PAYMENT_FAILED);
            //revert showSeat status as AVAILABLE
            for(ShowSeat showSeat:showSeats){
                showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            }
        }
        return entityToTicketResponseDto(ticketRepository.save(ticket));
    }
    @Override
    public Boolean deleteTicket(int id) {
        Ticket ticket = ticketRepository.findById(id).
                orElseThrow(()-> new TicketNotFoundException("Ticket Not Found for id "+ id));
        User user =userRepository.findUserByTicketId(id);
        if(user==null)
            throw new UserNotFoundException("User not found for id "+id);
        user.getTickets().remove(ticket);
        ticketRepository.delete(ticket);
        userRepository.save(user);
        return true;
    }

    @Override
    public TicketResponseDto getTicket(int id) {
        Ticket savedTicket= ticketRepository.findById(id).
                orElseThrow(()->new TicketNotFoundException("Ticket not found for id "+id));
        return entityToTicketResponseDto(savedTicket);
    }
    public TicketResponseDto entityToTicketResponseDto(Ticket ticket){
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setTicketId(ticket.getId());
        ticketResponseDto.setUserId(ticket.getUser().getId());
        ticketResponseDto.setBookingTime(ticket.getBookingTime());
        List<Integer>showSeatIds = new ArrayList<>();
        for(ShowSeat showSeat:ticket.getShowSeat())
            showSeatIds.add(showSeat.getId());
        ticketResponseDto.setShowSeatId(showSeatIds);
        ticketResponseDto.setTotalAmount(ticket.getTotalAmount());
        ticketResponseDto.setShowTime(ticket.getShowSeat().get(0).getShow().getStartTime());
        ticketResponseDto.setTicketStatus(ticket.getTicketStatus());
        return ticketResponseDto;

    }
    public Ticket ticketRequestDtoToEntity(TicketRequestDto ticketRequestDto){
        Ticket ticket = new Ticket();
        double totalAmount=0;
        User user =userRepository.findById(ticketRequestDto.getUserId()).
                orElseThrow(()-> new UserNotFoundException("User not found for id "+ticketRequestDto.getUserId()));
        ticket.setUser(user);
        ticket.setTicketStatus(TicketStatus.PENDING_PAYMENT);
        ticket.setBookingTime(Instant.now());
        List<ShowSeat>showSeats=new ArrayList<>();
        for(int showSeatId:ticketRequestDto.getShowSeatId()){
            ShowSeat showSeat=showSeatRepository.findById(showSeatId).
                    orElseThrow(()->new ShowSeatNotFoundException("Show Seat not found for id "+showSeatId));
            totalAmount+=showSeat.getPrice();
            showSeats.add(showSeat);
        }
        ticket.setShowSeat(showSeats);
        ticket.setTotalAmount(totalAmount);
        return ticket;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void checkAndLockSeats(List<ShowSeat>showSeats){
        for(ShowSeat showSeat:showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE))
                throw new SeatNotAvailableException("Selected seat not available anymore");
        }
        for(ShowSeat showSeat:showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
        }
    }
    public boolean makePayment(){
        return true;

    }
    public List<ShowSeat>getShowSeatById(List<Integer>showSeatIds){
        List<ShowSeat>showSeats=new ArrayList<>();
        for(int showSeatId:showSeatIds){
            ShowSeat showSeat=showSeatRepository.findById(showSeatId).
                    orElseThrow(()->new ShowSeatNotFoundException("Show Seat not found for id "+showSeatId));
            showSeats.add(showSeat);
        }
        return showSeats;
    }

}
