package com.hai.minh.ecommerce.third_party.agility.services.impl;

import com.hai.minh.ecommerce.third_party.agility.dtos.branch.BranchDTO;
import com.hai.minh.ecommerce.third_party.agility.services.BranchService;
import com.hai.minh.ecommerce.utils.RestClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
    private final Logger log = LoggerFactory.getLogger(BranchServiceImpl.class);

    @Resource
    private RestClientUtil restClientUtil;

    @Override
    public List<BranchDTO> getBranchList() {
//        return restClientUtil.callRest();
        return Collections.emptyList();
    }

}
