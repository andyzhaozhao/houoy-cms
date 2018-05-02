package com.houoy.cms.service.impl;

import com.houoy.cms.dao.ApkMapper;
import com.houoy.cms.service.ApkService;
import com.houoy.cms.vo.ApkVO;
import com.houoy.common.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("apkService")
public class ApkServiceImpl extends BaseServiceImpl<ApkMapper, ApkVO> implements ApkService<ApkVO> {

    @Override
    @Autowired
    protected void setService(ApkMapper _mapper) {
        mapper = _mapper;
    }

    @Override
    public ApkVO retrieveLast() throws RuntimeException {
        return mapper.retrieveLast();
    }

}