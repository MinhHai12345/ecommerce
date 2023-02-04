package com.hai.minh.ecommerce.entities.commons;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hai.minh.ecommerce.commons.constants.Constants;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1931996178528043954L;
    private Integer id;
    private boolean isDeleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "is_deleted")
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT_DDMMYYYYHHMMSS_HYPHEN, timezone = Constants.GLOBAL_TIMEZONE)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT_DDMMYYYYHHMMSS_HYPHEN, timezone = Constants.GLOBAL_TIMEZONE)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    private void onCreated() {
        this.createdAt = this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    private void onUpdated() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
