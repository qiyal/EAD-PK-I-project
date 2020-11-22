package midka.services;

import midka.handlers.Handler;

public class AuthService {
    private static AuthService instance;
    private boolean auth;
    private String authUserLogin;
    private String role;
    private Handler handler;

    private AuthService() {
        this.auth = false;
        this.authUserLogin = "";
        this.role = "";
    }

    // INSTANCE
    public static AuthService getInstance() {
        if(instance == null)
            instance = new AuthService();
        return instance;
    }

    // METHODS
    public boolean doAuth(String login, String password) {
        if (handler.check(login, password)) {
            this.authUserLogin = login;
            this.auth = true;
            return true;
        }

        return false;
    }

    public void logOut() {
        auth = false;
        authUserLogin = "";
        role = "";
    }

    // SETTERS
    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public void setAuthUserLogin(String authUserLogin) {
        this.authUserLogin = authUserLogin;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    // GETTERS
    public String getAuthUserLogin() {
        return authUserLogin;
    }

    public String getRole() {
        return role;
    }
}
