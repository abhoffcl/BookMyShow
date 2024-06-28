package dev.Abhishek.BookMyShow.exception;

public class SeatNotAvailableException extends RuntimeException{
    public SeatNotAvailableException(String message) {
        super(message);
    }
}
