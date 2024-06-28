package dev.Abhishek.BookMyShow.exception;

public class ShowNotFoundException extends RuntimeException{
    public ShowNotFoundException(String message) {
        super(message);
    }
}
