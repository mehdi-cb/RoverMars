package fr.codebusters.exceptions;

public class InvalidRoverInstruction extends RuntimeException {
    public InvalidRoverInstruction(String message) {
        super(message);
    }
}
