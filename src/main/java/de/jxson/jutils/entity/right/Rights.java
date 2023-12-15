package de.jxson.jutils.entity.right;

import de.jxson.jutils.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Rights extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String rightName;
    private String rightDescription;

}
