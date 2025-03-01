package ru.noxly.authorization.exceptions.users;

import ru.noxly.authorization.exceptions.GlobalAppException;

public class UserNotFoundExc extends GlobalAppException {
    public UserNotFoundExc() {
        super(404, "User not found");
    }
}
