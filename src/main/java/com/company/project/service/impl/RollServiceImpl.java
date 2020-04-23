package com.company.project.service.impl;

import com.company.project.dao.RollMapper;
import com.company.project.model.Roll;
import com.company.project.service.RollService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/04/23.
 */
@Service
@Transactional
public class RollServiceImpl extends AbstractService<Roll> implements RollService {
    @Resource
    private RollMapper rollMapper;

}
