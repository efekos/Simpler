package me.efekos.simpler.exception;

/**
 * Gets thrown when there is an annotation missing, or invalid.
 */
public class InvalidAnnotationException extends Exception{
    public InvalidAnnotationException() {
    }

    public InvalidAnnotationException(String message) {
        super(message);
    }

    public InvalidAnnotationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAnnotationException(Throwable cause) {
        super(cause);
    }

    public InvalidAnnotationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
