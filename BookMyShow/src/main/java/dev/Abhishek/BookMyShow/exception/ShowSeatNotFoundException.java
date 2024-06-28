package dev.Abhishek.BookMyShow.exception;

public class ShowSeatNotFoundException extends RuntimeException{
    public ShowSeatNotFoundException(String message) {
        super(message);
    }
}
