package cave.entity;

import org.hibernate.annotations.CollectionId;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by Jeor on 2016/4/14.
 */
public class PmsPageElement extends Permission {
    private String code;

    public PmsPageElement() {    }

    public PmsPageElement(Date createDate, String description, Integer id, String name, Integer relationId, Integer status, Integer type, String code) {
        super(createDate, description, id, name, relationId, status, type);
        this.code = code;
    }
    @Column(name="code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
