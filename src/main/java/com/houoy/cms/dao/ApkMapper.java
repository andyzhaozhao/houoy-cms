package com.houoy.cms.dao;

import com.houoy.cms.vo.ApkVO;
import com.houoy.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author andyzhao
 */
@Mapper
public interface ApkMapper extends BaseMapper<ApkVO> {
    //查询最新的安装包
    ApkVO retrieveLast() throws RuntimeException;
}
