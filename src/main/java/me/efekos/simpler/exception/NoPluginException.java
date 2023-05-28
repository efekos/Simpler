package me.efekos.simpler.exception;

public class NoPluginException extends Exception{
    public NoPluginException() {
    }

    public NoPluginException(String message) {
        super(message);
    }

    public NoPluginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPluginException(Throwable cause) {
        super(cause);
    }

    public NoPluginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
