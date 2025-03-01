package ru.noxly.fuelseller.exceptions;

public class GeneralException extends GlobalAppException {
    public GeneralException(int status, String message) {
        super(status, message);
    }
}
