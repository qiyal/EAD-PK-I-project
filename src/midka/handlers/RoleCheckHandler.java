package midka.handlers;

import midka.services.AuthService;
import midka.singleton.DBUser;

public class RoleCheckHandler extends Handler {
    private AuthService authService = AuthService.getInstance();
    private DBUser dbUser = DBUser.getInstance();

    @Override
    public boolean check(String login, String password) {
        if (dbUser.isAdmin(login)) {
            authService.setRole("Admin");
        } else {
            authService.setRole("Customer");
        }

        return checkNext(login, password);
    }
}
