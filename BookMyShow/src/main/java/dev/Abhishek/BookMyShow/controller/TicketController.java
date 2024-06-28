package dev.Abhishek.BookMyShow.controller;
import dev.Abhishek.BookMyShow.dto.ActorRequestDto;
import dev.Abhishek.BookMyShow.dto.ActorResponseDto;
import dev.Abhishek.BookMyShow.dto.TicketRequestDto;
import dev.Abhishek.BookMyShow.dto.TicketResponseDto;
import dev.Abhishek.BookMyShow.exception.InvalidInputException;
import dev.Abhishek.BookMyShow.service.ticket.TicketService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/")
    ResponseEntity<TicketResponseDto> createTicket(@RequestBody TicketRequestDto ticketRequestDto){
        return ResponseEntity.ok(ticketService.createTicket(ticketRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDto>getTicket(@PathVariable("id")int id){
        if(id<1)
            throw new InvalidInputException("Enter valid Ticket id ");
        return ResponseEntity.ok(ticketService.getTicket(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>deleteTicket(@PathVariable("id")int id){
        if(id<1)
            throw new InvalidInputException("Enter valid Ticket id ");
        return ResponseEntity.ok(ticketService.deleteTicket(id));
    }

}
