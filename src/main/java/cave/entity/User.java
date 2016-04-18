package cave.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jeor on 2016/1/28.
 */

@Entity
@Table(name="[user]")
public class User implements java.io.Serializable{
    private int id;
    private String email;
    private String phone;
    private String qqNumber;
    private String name;
    private String password;

    private List<UserGroup> userGroups ;
    private List<Role> roles ;
    private List<Permission> permissions;

    public User() {
    }

    public User(String email, int id, String name, String password, String phone, String qqNumber) {
        this.email = email;
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.qqNumber = qqNumber;
    }

    @Column(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name="qqNumber")
    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }
    @Transient
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
    @Transient
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    @Transient
    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }
}
