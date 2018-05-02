package com.houoy.cms.service.rest;

import com.houoy.cms.config.CommonConfig;
import com.houoy.cms.config.NginxConfig;
import com.houoy.cms.service.ApkService;
import com.houoy.cms.vo.ApkVO;
import com.houoy.common.utils.FileUtil;
import com.houoy.common.utils.JqueryDataTablesUtil;
import com.houoy.common.vo.JquryDataTablesVO;
import com.houoy.common.vo.PageResultVO;
import com.houoy.common.vo.RequestResultVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * apk安装包管理
 * Created by andyzhao on 2017/5/2.
 */
@RestController
@RequestMapping("/api/apk")
public class ApkController {
    private static final Log logger = LogFactory.getLog(ApkController.class);

    @Resource
    private ApkService apkService;

    @Autowired
    private NginxConfig nginxConfig;

    @Autowired
    private CommonConfig commonConfig;

    @GetMapping(value = "/check")
    public ApkVO check() {
        return apkService.retrieveLast();
    }

    @ResponseBody
    @RequestMapping("/save")
    //上传图片和属性
    public RequestResultVO add(ApkVO apkVO, HttpServletRequest request) throws IOException {
        Integer num = 0;
        RequestResultVO resultVO = new RequestResultVO();
        if (apkVO != null) {
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            if (files != null && files.size() > 0) {
                MultipartFile file = files.get(0);
                String fileName = file.getOriginalFilename();
                String localPath = nginxConfig.getPathApk() + apkVO.getUrl() + File.separator + fileName;
                FileUtil.save(file.getInputStream(), localPath);

                String url = nginxConfig.getHttpUrl() + apkVO.getUrl() + File.separator + fileName;
                apkVO.setUrl(url);
                if (apkVO.getPk_apk() != null) {
                    //如果前端传递过来pk,则判断为更新操作
                    num = apkService.updateByVO(apkVO);
                } else {
                    num = apkService.saveByVO(apkVO);
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

    @PostMapping("/delete")
    public RequestResultVO delete(@RequestBody List<String> pk_roles) {
        Integer num = apkService.deleteByPK(pk_roles);
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

    @ApiOperation(value = "分页获取运动视频列表")
    @ApiImplicitParam(name = "apkVO", value = "视频筛选信息", required = true, paramType = "query", dataType = "ApkVO")
    @GetMapping(value = "/retrieve")
    //返回带有图片路径的datatable数据
    public JquryDataTablesVO<ApkVO> retrieve(ApkVO apkVO, HttpServletRequest request) {
        apkVO = (ApkVO) JqueryDataTablesUtil.filterParam(apkVO, request);
        List<ApkVO> result = apkService.retrieveAllWithPage(apkVO);
        Long count = apkService.retrieveAllCount(apkVO);
        JquryDataTablesVO rtv = JqueryDataTablesUtil.madeJqueryDatatablesVO(Long.valueOf(count), result);
        return rtv;
    }

    @ApiOperation(value = "移动端接口，分页获取运动视频列表")
    @GetMapping("/retrieveMobile")
    //返回带有图片路径的datatable数据
    public PageResultVO retrieveMobile(ApkVO apkVO) {
        List<ApkVO> result = apkService.retrieveAllWithPage(apkVO);
        Long count = apkService.retrieveAllCount(apkVO);
        PageResultVO pageResultVO = new PageResultVO();
        pageResultVO.setSuccess(true);
        pageResultVO.setMsg("查询成功");
        pageResultVO.setResultData(result);
        pageResultVO.setStart(apkVO.getStart());
        pageResultVO.setLength(apkVO.getLength());
        pageResultVO.setOrderColumnName(apkVO.getOrderColumnName());
        pageResultVO.setOrderDir(apkVO.getOrderDir());
        pageResultVO.setTotal(count);
        return pageResultVO;
    }
}
