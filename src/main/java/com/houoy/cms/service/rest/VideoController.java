package com.houoy.cms.service.rest;

import com.houoy.cms.config.NginxConfig;
import com.houoy.cms.service.VideoService;
import com.houoy.cms.utils.SftpUtils;
import com.houoy.cms.vo.VideoVO;
import com.houoy.common.utils.JqueryDataTablesUtil;
import com.houoy.common.vo.JquryDataTablesVO;
import com.houoy.common.vo.RequestResultVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 视频管理
 * Created by andyzhao on 2017/5/2.
 */
@Controller
@RequestMapping("/api/video")
public class VideoController {
    private static final Log logger = LogFactory.getLog(VideoController.class);

    @Resource
    private VideoService videoService;

    @Autowired
    private NginxConfig nginxConfig;

    @ResponseBody
    @RequestMapping("/save")
    public RequestResultVO add(VideoVO videoVO, HttpServletRequest request) throws IOException {
        Integer num = 0;
        RequestResultVO resultVO = new RequestResultVO();
        if (videoVO != null) {
            if (videoVO.getPk_video() != null) {//如果前端传递过来pk,则判断为更新操作
                num = videoService.updateByVO(videoVO);
            } else {
                num = videoService.saveByVO(videoVO);
            }

            if (num >= 1) {
                List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
                if (files != null && files.size() > 0) {
                    MultipartFile file = files.get(0);
                    String fileName = file.getOriginalFilename();
                    SftpUtils sftpUtils = new SftpUtils(nginxConfig.getUrl(), nginxConfig.getPort(), nginxConfig.getUser()
                            , nginxConfig.getPass());
                    sftpUtils.upload(nginxConfig.getPath() + "/" + videoVO.getPath(), file.getInputStream(), fileName);
                }

                resultVO.setSuccess(true);
                resultVO.setMsg("保存成功");
                resultVO.setResultData(num);
            } else {
                resultVO.setSuccess(false);
                resultVO.setMsg("保存失败");
            }
        } else {
            resultVO.setSuccess(false);
            resultVO.setMsg("参数不可为null");
        }
        return resultVO;
    }

    @ResponseBody
    @PostMapping("/delete")
    public RequestResultVO delete(@RequestBody List<String> pk_roles) {
        Integer num = videoService.deleteByPK(pk_roles);
        RequestResultVO resultVO = new RequestResultVO();
        if (num >= 1) {
            resultVO.setSuccess(true);
            resultVO.setMsg("查询成功");
            resultVO.setResultData(num);
        } else {
            resultVO.setSuccess(false);
            resultVO.setMsg("保存");
        }

        return resultVO;
    }

    @ResponseBody
    @RequestMapping(value = "/retrieve")
    //返回带有图片路径的datatable数据
    public JquryDataTablesVO<VideoVO> retrieve(VideoVO vo, HttpServletRequest request) {
        vo = (VideoVO) JqueryDataTablesUtil.filterParam(vo, request);
        List<VideoVO> result = videoService.retrieveAllWithPage(vo);
        Long count = videoService.retrieveAllCount();
        JquryDataTablesVO rtv = JqueryDataTablesUtil.madeJqueryDatatablesVO(Long.valueOf(count), result);
        return rtv;
    }

}
