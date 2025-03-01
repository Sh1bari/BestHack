package ru.noxly.authorization.exceptions;

public class GeneralException extends GlobalAppException {
    public GeneralException(int status, String message) {
        super(status, message);
    }
}
