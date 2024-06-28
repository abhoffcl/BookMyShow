package dev.Abhishek.BookMyShow.service.user;

import dev.Abhishek.BookMyShow.dto.TicketResponseDto;
import dev.Abhishek.BookMyShow.dto.UserLoginRequestDto;
import dev.Abhishek.BookMyShow.dto.UserResponseDto;
import dev.Abhishek.BookMyShow.dto.UserSignupRequestDto;
import dev.Abhishek.BookMyShow.exception.*;
import dev.Abhishek.BookMyShow.model.Ticket;
import dev.Abhishek.BookMyShow.model.User;
import dev.Abhishek.BookMyShow.repository.UserRepository;
import dev.Abhishek.BookMyShow.service.ticket.TicketService;
import dev.Abhishek.BookMyShow.service.ticket.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketService ticketService;
    @Override

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UserResponseDto signup(UserSignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        if(userRepository.findUserByEmail(email)!=null)
            throw new UserAlreadyExistException("User with email " +email+" already exists");
        User user = userRequestDtoToEntity(signupRequestDto);
        return(entityToUserResponseDto(userRepository.save(user)));
    }
    @Override
    public UserResponseDto login(UserLoginRequestDto loginRequestDto) {
        String email= loginRequestDto.getEmail();
        String password =loginRequestDto.getPassword();
        User savedUser = userRepository.findUserByEmail(email);
        if(savedUser == null)
            throw new UserNotFoundException("User with email " +email+" does not exist");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(password, savedUser.getPassword()))
            return entityToUserResponseDto(savedUser);
        else
            throw new InvalidUserCredentials("Invalid password");
    }
    @Override
    public List<TicketResponseDto> getBookingHistory(int id) {
        User user= userRepository.findById(id).
                orElseThrow(()->new UserNotFoundException("User not found for id "+id));
        List<TicketResponseDto>ticketResponseDtos = new ArrayList<>();
        List<Ticket>tickets =user.getTickets();
        if(tickets!=null){
            for(Ticket ticket:tickets)
                ticketResponseDtos.add(((TicketServiceImpl)ticketService).entityToTicketResponseDto(ticket));
        }
        return ticketResponseDtos;
    }
    public boolean deleteUser(int id){
        User user= userRepository.findById(id).
                orElseThrow(()->new UserNotFoundException("User not found for id "+id));
        userRepository.delete(user);
        return true;
    }
    @Override
    public UserResponseDto getUser(int id) {
        User savedUser= userRepository.findById(id).
                orElseThrow(()->new UserNotFoundException("User not found for id "+id));
        return entityToUserResponseDto(savedUser);
    }
    @Override
    public UserResponseDto updateUser(int id, UserSignupRequestDto signupRequestDto) {
        User savedUser= userRepository.findById(id).
                orElseThrow(()->new UserNotFoundException("User not found for id "+id));
        if(signupRequestDto.getName()!=null)
            savedUser.setName(signupRequestDto.getName());
        if(signupRequestDto.getEmail()!=null)
            savedUser.setEmail(signupRequestDto.getEmail());
        if(signupRequestDto.getPassword()!=null)
            savedUser.setPassword(signupRequestDto.getPassword());
        return entityToUserResponseDto(userRepository.save(savedUser));
    }
    public UserResponseDto entityToUserResponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setId(user.getId());
        List<TicketResponseDto>ticketResponseDtos = new ArrayList<>();
        List<Ticket>tickets =user.getTickets();
        if(tickets!=null){
            for(Ticket ticket:tickets)
                ticketResponseDtos.add(((TicketServiceImpl)ticketService).entityToTicketResponseDto(ticket));
        }
        userResponseDto.setTickets(ticketResponseDtos);
        return userResponseDto;
    }
    public User userRequestDtoToEntity(UserSignupRequestDto userRequestDto){
        User user = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(encoder.encode(userRequestDto.getPassword()));
        return user;
    }
}
