package com.hai.minh.ecommerce.common.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.ZonedDateTime;

@Setter
@Getter
public class AbstractAuditableData extends AbstractData {
    @Serial
    private static final long serialVersionUID = 4723028201650913651L;

    private String createdBy;
    private ZonedDateTime createdDate;
    private String lastModifiedBy;
    private ZonedDateTime lastModifiedDate;
}
