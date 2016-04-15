package cave.entity;

import javax.persistence.*;

/**
 * Created by Jeor on 2016/1/28.
 */

@Entity
@Table(name="userGroup")
public class UserGroup implements java.io.Serializable{
    private int id;
    private String name;
    private int parentId;

    public UserGroup() {   }
    public UserGroup(int parentId, String name, int id) {
        this.parentId = parentId;
        this.name = name;
        this.id = id;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "perentId")
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
