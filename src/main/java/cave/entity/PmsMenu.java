package cave.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Jeor on 2016/4/14.
 */
@Entity
@Table(name = "pmsMenu")
public class PmsMenu extends Permission {

    private String url;
    private Integer parentId;

    public PmsMenu() { }
    public PmsMenu(Date createDate, String description, Integer id, String name, Integer relationId, Integer status, Integer type, Integer parentId, String url) {
        super(createDate, description, id, name, relationId, status, type);
        this.parentId = parentId;
        this.url = url;
    }
    @Column(name = "parentId")
    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    @Column(name = "url")
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
