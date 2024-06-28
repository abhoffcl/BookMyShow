package dev.Abhishek.BookMyShow.exception;

public class InvalidUserCredentials extends RuntimeException{
    public InvalidUserCredentials(String message) {
        super(message);
    }
}
