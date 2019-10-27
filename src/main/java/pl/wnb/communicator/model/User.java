package pl.wnb.communicator.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Table(name = "Users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", unique = true, nullable = false)
    private long user_id;
    @Column(name = "email", unique = true, nullable = false)
    @Email
    private String email;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, columnDefinition = "boolean default TRUE")
    private boolean active;
    @Column(name = "public_key", unique = true)
    private byte[] publicKey;

    @Column(name = "key_email")
    @Email
    private String keyEmail;


    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = true;
        this.publicKey = null;
        this.publicKey = null;
    }

    private User() {
        this.active = true;
        this.publicKey = null;
    }

    public String getKeyEmail() {
        return keyEmail;
    }

    public void setKeyEmail(String keyEmail) {
        this.keyEmail = keyEmail;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", publicKey=" + Arrays.toString(publicKey) +
                ", keyEmail='" + keyEmail + '\'' +
                '}';
    }
}
