package com.houoy.cms.service;

import com.houoy.cms.vo.FolderVO;
import com.houoy.common.service.BaseService;

import java.util.List;

/**
 * Created by andyzhao on 2017-02-20.
 */
public interface FolderService extends BaseService<FolderVO> {
    List<FolderVO> retrieveByParentPK(List<String> pks) ;
}
