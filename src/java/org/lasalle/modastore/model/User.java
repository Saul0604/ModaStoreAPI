package org.lasalle.modastore.model;

public class User {

    private int idUser;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private int idRole;
    private boolean active;

    public User() {
    }

    public User(int idUser, String fullName, String email, String username, String password, int idRole, boolean active) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.idRole = idRole;
        this.active = active;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
        public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
