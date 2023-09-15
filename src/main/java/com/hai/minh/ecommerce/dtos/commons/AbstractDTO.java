package com.hai.minh.ecommerce.dtos.commons;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hai.minh.ecommerce.commons.constants.Constants;

import java.io.Serializable;
import java.sql.Timestamp;

public class AbstractDTO implements Serializable {
    private static final long serialVersionUID = -6058567028022955338L;

    private Integer id;
    private boolean isDeleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("is_deleted")
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @JsonProperty("created_at")
    @JsonFormat(pattern = Constants.DATE_FORMAT_DDMMYYYYHHMMSS_HYPHEN, timezone = Constants.GLOBAL_TIMEZONE)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    @JsonFormat(pattern = Constants.DATE_FORMAT_DDMMYYYYHHMMSS_HYPHEN, timezone = Constants.GLOBAL_TIMEZONE)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
