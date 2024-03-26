package com.hai.minh.ecommerce.third_party.agility.dtos.branch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BranchDTO implements Serializable {
    private static final long serialVersionUID = -2692049169421817564L;

    @JsonProperty("BranchID")
    private String branchID;

    @JsonProperty("ProfName")
    private String profName;

    @JsonProperty("CompanyName")
    private String companyName;

    @JsonProperty("BranchGUID")
    private String branchGUID;
}
