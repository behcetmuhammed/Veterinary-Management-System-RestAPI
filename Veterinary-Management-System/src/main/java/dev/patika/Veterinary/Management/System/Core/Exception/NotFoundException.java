package dev.patika.Veterinary.Management.System.Core.Exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
