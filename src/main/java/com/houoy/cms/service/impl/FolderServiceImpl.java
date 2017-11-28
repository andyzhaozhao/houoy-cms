package com.houoy.cms.service.impl;

import com.houoy.cms.dao.FolderMapper;
import com.houoy.cms.service.FolderService;
import com.houoy.cms.vo.FolderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by andyzhao on 2017/2/14 0014.
 */
@Service("folderService")
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderMapper folderMapper;

    @Override
    public List<FolderVO> retrieveAll() {
        return folderMapper.retrieveAll();
    }

    @Override
    public List<FolderVO> retrieveByPK(String pk) {
        return null;
    }

    @Override
    public Long retrieveAllCount(FolderVO vo) throws RuntimeException {
        return folderMapper.retrieveAllCount( vo);
    }

    @Override
    public List<FolderVO> retrieveAllWithPage(FolderVO vo) throws RuntimeException {
        return folderMapper.retrieveAllWithPage(vo);
    }


    @Override
    public Integer saveByVO(FolderVO vo) {
        return folderMapper.saveByVO(vo);
    }

    @Override
    public Integer updateByVO(FolderVO vo) {
        return folderMapper.updateByVO(vo);
    }

    @Override
    public Integer deleteByPK(String pk) {
        return folderMapper.deleteByPK(pk);
    }

    @Override
    public Integer deleteByPK(List<String> pks) {
        return folderMapper.deleteByPKs(pks);
    }


    @Override
    public List<FolderVO> retrieveByParentPK(List<String> pks) {
        return folderMapper.retrieveByParentPK(pks);
    }
}
