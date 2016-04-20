package cave.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jeor on 2016/1/28.
 */

@Entity
@Table(name="userGroup")
public class UserGroup implements java.io.Serializable{
    private Integer id;
    private String name;
    private Integer parentId;

    private List<User> users;
    private List<Role> roles;
    private List<Permission> permissions;

    public UserGroup() {   }
    public UserGroup(Integer parentId, String name, Integer id) {
        this.parentId = parentId;
        this.name = name;
        this.id = id;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "parentId")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
