package de.jxson.jutils.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@MappedSuperclass
public class BaseEntity {

    @JsonIgnore
    @Column(name = "acquisitionTime")
    @CreationTimestamp
    private Timestamp acquisitionTime;

    @JsonIgnore
    @Column(name = "updateTime")
    @UpdateTimestamp
    private Timestamp updateTime;

    public Timestamp getAcquisitionTime() {
        return acquisitionTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }
}
