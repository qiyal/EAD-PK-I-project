package midka.handlers;

import midka.singleton.DBUser;

public class BaseAuthHandler extends Handler {

    private DBUser dbUser = DBUser.getInstance();
    
    @Override
    public boolean check(String login, String password) {
        if (dbUser.checkUser(login, password)) {
            return checkNext(login, password);
        }

        return false;
    }
}
