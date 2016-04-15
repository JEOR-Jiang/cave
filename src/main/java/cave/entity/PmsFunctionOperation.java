package cave.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Jeor on 2016/4/14.
 */
@Entity
@Table(name="pmsFunctionOperation")
public class PmsFunctionOperation extends Permission{
    private Integer parentId;
    private String code;
    private String preURL;

    public PmsFunctionOperation() {}
    public PmsFunctionOperation(Date createDate, String description, Integer id, String name, Integer relationId, Integer status, Integer type, String code, Integer parentId, String preURL) {
        super(createDate, description, id, name, relationId, status, type);
        this.code = code;
        this.parentId = parentId;
        this.preURL = preURL;
    }

    @Column(name="code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    @Column(name="parentId")
    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    @Column(name="preURL")
    public String getPreURL() {
        return preURL;
    }
    public void setPreURL(String preURL) {
        this.preURL = preURL;
    }
}
