package com.houoy.cms.service.impl;

import com.houoy.cms.dao.VideoMapper;
import com.houoy.cms.service.VideoService;
import com.houoy.cms.vo.VideoVO;
import com.houoy.common.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("videoService")
public class VideoServiceImpl extends BaseServiceImpl<VideoMapper, VideoVO> implements VideoService {

    @Override
    @Autowired
    protected void setService(VideoMapper _mapper) {
        mapper = _mapper;
    }
}