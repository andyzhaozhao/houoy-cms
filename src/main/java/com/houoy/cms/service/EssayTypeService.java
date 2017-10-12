package com.houoy.cms.service;

import com.houoy.cms.vo.EssayTypeVO;
import com.houoy.common.service.BaseService;

import java.util.List;

/**
 * Created by andyzhao on 2017-02-20.
 */
public interface EssayTypeService extends BaseService<EssayTypeVO> {
    List<EssayTypeVO> retrieveByParentPK(List<String> pks);
}
