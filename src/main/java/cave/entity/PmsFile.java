package cave.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * Created by Jeor on 2016/4/14.
 */
@Entity
@Table(name = "pmsFile")
public class PmsFile extends Permission{
    private String path;

    public PmsFile() {    }

    public PmsFile(Date createDate, String description, Integer id, String name, Integer relationId, Integer status, Integer type, String path) {
        super(createDate, description, id, name, relationId, status, type);
        this.path = path;
    }
    @Column(name="path")
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
