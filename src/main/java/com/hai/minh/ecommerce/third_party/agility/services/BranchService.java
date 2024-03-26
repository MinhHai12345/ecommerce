package com.hai.minh.ecommerce.third_party.agility.services;

import com.hai.minh.ecommerce.third_party.agility.dtos.branch.BranchDTO;

import java.util.List;

public interface BranchService {

    /**
     * Get list branch from Agility
     */
    List<BranchDTO> getBranchList();
}
