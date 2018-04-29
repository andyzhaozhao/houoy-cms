package com.houoy.cms.service.impl;

import com.houoy.cms.dao.FolderApkMapper;
import com.houoy.cms.service.FolderApkService;
import com.houoy.cms.vo.FolderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by andyzhao on 2017/2/14 0014.
 */
@Service("folderApkService")
public class FolderApkServiceImpl implements FolderApkService {

    @Autowired
    private FolderApkMapper folderApkMapper;

    @Override
    public List<FolderVO> retrieveAll() {
        return folderApkMapper.retrieveAll();
    }

    @Override
    public List<FolderVO> retrieveByPK(String pk) {
        return null;
    }

    @Override
    public Long retrieveAllCount(FolderVO vo) throws RuntimeException {
        return folderApkMapper.retrieveAllCount(vo);
    }

    @Override
    public List<FolderVO> retrieveAllWithPage(FolderVO vo) throws RuntimeException {
        return folderApkMapper.retrieveAllWithPage(vo);
    }


    @Override
    public Integer saveByVO(FolderVO vo) {
        return folderApkMapper.saveByVO(vo);
    }

    @Override
    public Integer updateByVO(FolderVO vo) {
        return folderApkMapper.updateByVO(vo);
    }

    @Override
    public Integer deleteByPK(String pk) {
        return folderApkMapper.deleteByPK(pk);
    }

    @Override
    public Integer deleteByPK(List<String> pks) {
        return folderApkMapper.deleteByPKs(pks);
    }


    @Override
    public List<FolderVO> retrieveByParentPK(List<String> pks) {
        return folderApkMapper.retrieveByParentPK(pks);
    }
}
