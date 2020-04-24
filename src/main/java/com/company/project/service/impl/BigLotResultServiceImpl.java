package com.company.project.service.impl;

import com.company.project.dao.BigLotResultMapper;
import com.company.project.model.BigLotResult;
import com.company.project.service.BigLotResultService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/04/23.
 */
@Service
@Transactional
public class BigLotResultServiceImpl extends AbstractService<BigLotResult> implements BigLotResultService {
    @Resource
    private BigLotResultMapper bigLotResultMapper;

}
