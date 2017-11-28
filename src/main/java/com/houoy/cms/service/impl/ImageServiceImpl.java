package com.houoy.cms.service.impl;

import com.houoy.cms.dao.ImageMapper;
import com.houoy.cms.service.ImageService;
import com.houoy.cms.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by andyzhao on 2017/2/14 0014.
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public List<ImageVO> retrieveAll() {
        return imageMapper.retrieveAll();
    }

    @Override
    public List<ImageVO> retrieveByPK(String pk) {
        return null;
    }

    @Override
    public Long retrieveAllCount(ImageVO vo) throws RuntimeException {
        return imageMapper.retrieveAllCount(vo);
    }

    @Override
    public List<ImageVO> retrieveAllWithPage(ImageVO vo) throws RuntimeException {
        return imageMapper.retrieveAllWithPage(vo);
    }

    @Override
    public Integer saveByVO(ImageVO vo) {
        return imageMapper.saveByVO(vo);
    }

    @Override
    public Integer updateByVO(ImageVO vo) {
        return imageMapper.updateByVO(vo);
    }

    @Override
    public Integer deleteByPK(String pk) {
        return imageMapper.deleteByPK(pk);
    }

    @Override
    public Integer deleteByPK(List<String> pks) {
        return imageMapper.deleteByPKs(pks);
    }

    @Override
    public List<ImageVO> retrieveByParentPK(List<String> pks) {
        return null;
    }

}
