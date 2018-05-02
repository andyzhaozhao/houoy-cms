package com.houoy.cms.service;

import com.houoy.cms.vo.ApkVO;
import com.houoy.common.service.BaseService;
import com.houoy.common.vo.SuperVO;

public interface ApkService<T extends SuperVO> extends BaseService<T> {
    ApkVO retrieveLast() throws RuntimeException;
}

