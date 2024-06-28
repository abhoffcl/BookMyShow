package dev.Abhishek.BookMyShow.exception;

public class TheatreNotFoundException extends RuntimeException{
    public TheatreNotFoundException(String message) {
        super(message);
    }
}
