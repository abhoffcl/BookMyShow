package dev.Abhishek.BookMyShow.exception;

public class SeatNotFoundException extends RuntimeException{
    public SeatNotFoundException(String message) {
        super(message);
    }
}
