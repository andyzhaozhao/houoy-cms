package com.houoy.cms.dao;

import com.houoy.cms.vo.FolderVO;
import com.houoy.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author andyzhao
 */
@Mapper
public interface FolderVideoMapper extends BaseMapper<FolderVO> {
    List<FolderVO> retrieveByParentPK(List<String> pks) ;
}
