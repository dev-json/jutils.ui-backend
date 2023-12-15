package de.jxson.jutils.entity.right;

import de.jxson.jutils.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Rights extends BaseEntity {

    @Id
    private Integer id;

    private String rightName;
    private String rightDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public String getRightDescription() {
        return rightDescription;
    }

    public void setRightDescription(String rightDescription) {
        this.rightDescription = rightDescription;
    }
}
