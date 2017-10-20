package com.houoy.cms.service.impl;

import com.houoy.cms.dao.FolderMapper;
import com.houoy.cms.dao.FolderVideoMapper;
import com.houoy.cms.service.FolderService;
import com.houoy.cms.service.FolderVideoService;
import com.houoy.cms.vo.FolderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by andyzhao on 2017/2/14 0014.
 */
@Service("folderVideoService")
public class FolderVideoServiceImpl implements FolderVideoService {

    @Autowired
    private FolderVideoMapper folderVideoMapper;

    @Override
    public List<FolderVO> retrieveAll() {
        return folderVideoMapper.retrieveAll();
    }

    @Override
    public List<FolderVO> retrieveByPK(String pk) {
        return null;
    }

    @Override
    public Long retrieveAllCount() throws RuntimeException {
        return folderVideoMapper.retrieveAllCount();
    }

    @Override
    public List<FolderVO> retrieveAllWithPage(FolderVO vo) throws RuntimeException {
        return folderVideoMapper.retrieveAllWithPage(vo);
    }


    @Override
    public Integer saveByVO(FolderVO vo) {
        return folderVideoMapper.saveByVO(vo);
    }

    @Override
    public Integer updateByVO(FolderVO vo) {
        return folderVideoMapper.updateByVO(vo);
    }

    @Override
    public Integer deleteByPK(String pk) {
        return folderVideoMapper.deleteByPK(pk);
    }

    @Override
    public Integer deleteByPK(List<String> pks) {
        return folderVideoMapper.deleteByPKs(pks);
    }


    @Override
    public List<FolderVO> retrieveByParentPK(List<String> pks) {
        return folderVideoMapper.retrieveByParentPK(pks);
    }
}
