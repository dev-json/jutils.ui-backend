package de.jxson.jutils.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@MappedSuperclass
public class BaseEntity {

    @Column(name = "acquisitionTime")
    @CreationTimestamp
    private Timestamp acquisitionTime;

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
