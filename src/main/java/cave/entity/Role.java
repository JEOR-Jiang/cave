package cave.entity;

import javax.persistence.*;

/**
 * Created by Jeor on 2016/4/11.
 */

@Entity
@Table(name="role")
public class Role implements java.io.Serializable{
    private int id;
    private String name;
    private int status;

    public Role() { }
    public Role(int id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name="status")
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
