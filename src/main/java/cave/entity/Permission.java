package cave.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jeor on 2016/4/14.
 */
@Entity
@Table(name = "permission")
@Inheritance(strategy = InheritanceType.JOINED)
public class Permission implements Serializable {
    private Integer id;
    private Integer type;
    private Integer status;
    private Date createDate;
    private Integer relationId;
    private String name;
    private String description;

    public Permission() {}

    public Permission(Date createDate, String description, Integer id, String name, Integer relationId, Integer status, Integer type) {
        this.createDate = createDate;
        this.description = description;
        this.id = id;
        this.name = name;
        this.relationId = relationId;
        this.status = status;
        this.type = type;
    }

    @Column(name="createDate")
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
    @Column(name = "relationId")
    public Integer getRelationId() {
        return relationId;
    }
    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    @Column(name = "type")
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    @Column(name = "description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
