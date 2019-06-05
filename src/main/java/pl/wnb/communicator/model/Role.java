package pl.wnb.communicator.model;

import javax.persistence.*;

@Table(name = "Role")
@Entity
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long role_id;
    @Column(name = "role")
    private String role;

    private Role() {
    }

    public Role(String role) {

        this.role = role;

    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
