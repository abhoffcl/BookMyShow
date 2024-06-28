package dev.Abhishek.BookMyShow.controller;

import dev.Abhishek.BookMyShow.dto.*;
import dev.Abhishek.BookMyShow.exception.InvalidInputException;
import dev.Abhishek.BookMyShow.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    ResponseEntity<UserResponseDto> signup(@RequestBody UserSignupRequestDto userSignupRequestDto){
        String name= userSignupRequestDto.getName();
        String email = userSignupRequestDto.getEmail();
        String password = userSignupRequestDto.getPassword();
        if(name.isEmpty() || name.isBlank() || name==null)
            throw new InvalidInputException("Enter valid name");
        if(email.isEmpty() || email.isBlank() || email==null)
            throw new InvalidInputException("Enter valid email");
        if(password.isEmpty() || password.isBlank() || password==null)
            throw new InvalidInputException("Enter valid password");
        return ResponseEntity.ok(userService.signup(userSignupRequestDto));

    }
    @PostMapping("/login")
    ResponseEntity<UserResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto){
        String email = userLoginRequestDto.getEmail();
        String password = userLoginRequestDto.getPassword();
        if(email.isEmpty() || email.isBlank() || email==null)
            throw new InvalidInputException("Enter valid email");
        if(password.isEmpty() || password.isBlank() || password==null)
            throw new InvalidInputException("Enter valid password");
        return ResponseEntity.ok(userService.login(userLoginRequestDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id")int id, @RequestBody UserSignupRequestDto UserRequestDto){
        if(id<1)
            throw new InvalidInputException("Enter valid User id ");
        return ResponseEntity.ok(userService.updateUser(id,UserRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto>getUser(@PathVariable("id")int id){
        if(id<1)
            throw new InvalidInputException("Enter valid User id ");
        return ResponseEntity.ok(userService.getUser(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>deleteUser(@PathVariable("id")int id){
        if(id<1)
            throw new InvalidInputException("Enter valid User id ");
        return ResponseEntity.ok(userService.deleteUser(id));
    }
    @GetMapping("/history/{id}")
    public ResponseEntity<List<TicketResponseDto>>getBookingHistory(@PathVariable("id")int id){
        if(id<1)
            throw new InvalidInputException("Enter valid User id ");
        return ResponseEntity.ok(userService.getBookingHistory(id));

    }
}
