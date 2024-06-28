package dev.Abhishek.BookMyShow.service.user;

import dev.Abhishek.BookMyShow.dto.TicketResponseDto;
import dev.Abhishek.BookMyShow.dto.UserLoginRequestDto;
import dev.Abhishek.BookMyShow.dto.UserResponseDto;
import dev.Abhishek.BookMyShow.dto.UserSignupRequestDto;

import java.util.List;

public interface UserService {
     UserResponseDto signup(UserSignupRequestDto signupRequestDto);
     UserResponseDto login(UserLoginRequestDto loginRequestDto);
     UserResponseDto getUser(int id);
     UserResponseDto updateUser(int id,UserSignupRequestDto signupRequestDto);
     boolean deleteUser(int id);
     List<TicketResponseDto> getBookingHistory(int id);

}
