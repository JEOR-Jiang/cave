package cave.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jeor on 2016/4/11.
 */

@Entity
@Table(name="role")
public class Role implements java.io.Serializable{
    private Integer id;
    private String name;
    private Integer status;

    private List<UserGroup> userGroups;
    private List<User> users;
    private List<Role> roles;
    private List<Permission> permissions;
    private List<PmsMenu> pmsMenus;
    private List<PmsFile> pmsFiles;
    private List<PmsPageElement> pmsPageElements;
    private List<PmsFunctionOperation> pmsFunctionOperations;

    public Role() { }
    public Role(Integer id, String name, Integer status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Column(name="status")
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Transient
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
    @Transient
    public List<PmsFile> getPmsFiles() {
        return pmsFiles;
    }

    public void setPmsFiles(List<PmsFile> pmsFiles) {
        this.pmsFiles = pmsFiles;
    }
    @Transient
    public List<PmsFunctionOperation> getPmsFunctionOperations() {
        return pmsFunctionOperations;
    }

    public void setPmsFunctionOperations(List<PmsFunctionOperation> pmsFunctionOperations) {
        this.pmsFunctionOperations = pmsFunctionOperations;
    }
    @Transient
    public List<PmsMenu> getPmsMenus() {
        return pmsMenus;
    }

    public void setPmsMenus(List<PmsMenu> pmsMenus) {
        this.pmsMenus = pmsMenus;
    }
    @Transient
    public List<PmsPageElement> getPmsPageElements() {
        return pmsPageElements;
    }

    public void setPmsPageElements(List<PmsPageElement> pmsPageElements) {
        this.pmsPageElements = pmsPageElements;
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
    @Transient
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
