package com.houoy.cms.dao;

import com.houoy.cms.vo.EssayTypeVO;
import com.houoy.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author andyzhao
 */
@Mapper
public interface EssayTypeMapper extends BaseMapper<EssayTypeVO> {
    List<EssayTypeVO> retrieveByParentPK(List<String> pks);
}
