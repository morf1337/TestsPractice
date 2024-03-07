package edu.school21.models;

public class User {
    private Long identifier;
    private String login;
    private String password;
    private boolean authSuccess;

    public User(Long identifier, String login, String password, boolean authSuccess) {
        this.identifier = identifier;
        this.login = login;
        this.password = password;
        this.authSuccess = authSuccess;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthSuccess() {
        return authSuccess;
    }

    public void setAuthSuccess(boolean authSuccess) {
        this.authSuccess = authSuccess;
    }
}
