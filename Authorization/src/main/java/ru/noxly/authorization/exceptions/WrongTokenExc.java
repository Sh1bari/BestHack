package ru.noxly.authorization.exceptions;

public class WrongTokenExc extends GlobalAppException {
    public WrongTokenExc() {
        super(409, "Wrong token");
    }
}
