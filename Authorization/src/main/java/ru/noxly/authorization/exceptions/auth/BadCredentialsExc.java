package ru.noxly.authorization.exceptions.auth;

import ru.noxly.authorization.exceptions.GlobalAppException;

/**
 * @author Vladimir Krasnov
 */
public class BadCredentialsExc extends GlobalAppException {
    public BadCredentialsExc() {
        super(409, "Wrong username or password");
    }
}
