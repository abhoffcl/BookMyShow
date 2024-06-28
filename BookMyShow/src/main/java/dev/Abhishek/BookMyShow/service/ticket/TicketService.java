package dev.Abhishek.BookMyShow.service.ticket;

import dev.Abhishek.BookMyShow.dto.TicketRequestDto;
import dev.Abhishek.BookMyShow.dto.TicketResponseDto;
import org.springframework.http.ResponseEntity;

public interface TicketService {
    public TicketResponseDto createTicket(TicketRequestDto ticketRequestDto);
    public Boolean deleteTicket(int id);
    public TicketResponseDto getTicket(int id);

   // update ticket feature not provided

}
