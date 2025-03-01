package ru.noxly.authorization.exceptions.roles;

import ru.noxly.authorization.exceptions.GlobalAppException;

/**
 * @author Vladimir Krasnov
 */
public class RoleNotFoundExc extends GlobalAppException {
    public RoleNotFoundExc() {
        super(404, "Role not found");
    }
}
