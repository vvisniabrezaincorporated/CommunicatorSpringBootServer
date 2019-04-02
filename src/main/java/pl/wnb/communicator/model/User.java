package pl.wnb.communicator.model;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long user_id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "verification_points")
    private int verification_points;

    public User() {
    }

    public User(long user_id, String login, String password, String role, int verification_points) {
        this.user_id = user_id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.verification_points = verification_points;
    }

    public long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getVerification_points() {
        return this.verification_points;
    }

    public void setVerification_points(int verification_points) {
        this.verification_points = verification_points;
    }

    public User user_id(long user_id) {
        this.user_id = user_id;
        return this;
    }

    public User login(String login) {
        this.login = login;
        return this;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    public User role(String role) {
        this.role = role;
        return this;
    }

    public User verification_points(int verification_points) {
        this.verification_points = verification_points;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User users = (User) o;
        return user_id == users.user_id && Objects.equals(login, users.login)
                && Objects.equals(password, users.password) && Objects.equals(role, users.role)
                && verification_points == users.verification_points;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, login, password, role, verification_points);
    }

    @Override
    public String toString() {
        return "{" + " user_id='" + getUser_id() + "'" + ", login='" + getLogin() + "'" + ", password='" + getPassword()
                + "'" + ", role='" + getRole() + "'" + ", verification_points='" + getVerification_points() + "'" + "}";
    }

}