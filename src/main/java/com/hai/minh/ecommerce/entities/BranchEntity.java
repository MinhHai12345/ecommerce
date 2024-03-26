package com.hai.minh.ecommerce.entities;

import com.hai.minh.ecommerce.entities.commons.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "branches")
public class BranchEntity extends AbstractEntity {
    private static final long serialVersionUID = -8103900282810200591L;

    @Column(name = "branch_id")
    private String branchId;

    @Column(name = "branch_guid")
    private String branchGuid;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "profName")
    private String profName;

}
