package edu.school21.numbers;

public class IllegalNumberException extends RuntimeException {
    public IllegalNumberException() {
        super("Number must be greater than or equal to 2");
    }
}
