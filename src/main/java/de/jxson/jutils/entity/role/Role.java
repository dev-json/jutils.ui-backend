package de.jxson.jutils.entity.role;

import de.jxson.jutils.entity.BaseEntity;
import de.jxson.jutils.entity.right.Rights;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int priority;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "rolerights",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "right_id")
    )
    List<Rights> rights = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public List<Rights> getRights() {
        return rights;
    }
}
