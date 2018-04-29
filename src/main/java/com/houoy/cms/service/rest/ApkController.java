package com.houoy.cms.service.rest;

import com.houoy.cms.config.CommonConfig;
import com.houoy.cms.config.NginxConfig;
import com.houoy.cms.service.ApkService;
import com.houoy.cms.vo.ApkVO;
import com.houoy.common.utils.FileUtil;
import com.houoy.common.utils.JqueryDataTablesUtil;
import com.houoy.common.utils.SftpUtils;
import com.houoy.common.vo.JquryDataTablesVO;
import com.houoy.common.vo.PageResultVO;
import com.houoy.common.vo.RequestResultVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    
    @PostMapping("/save")
    public RequestResultVO add(@RequestBody ApkVO apkVO) throws IOException {
        Integer num = 0;
        RequestResultVO resultVO = new RequestResultVO();
        if (apkVO != null) {
            if (apkVO.getPk_apk() != null) {//如果前端传递过来pk,则判断为更新操作
                num = apkService.updateByVO(apkVO);
            } else {
                num = apkService.saveByVO(apkVO);
            }

            if (num >= 1) {
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

    //生成上传唯一编号
    private String generateUploadId() {
        String guid = UUID.randomUUID().toString();
        return guid.replace("-", "");
    }

    @PostMapping("/upload")
    public RequestResultVO upload(String uploadId, int total, int index, String path, HttpServletRequest request) throws IOException {
        RequestResultVO resultVO = new RequestResultVO();
        if (total < 1) {
            resultVO.setSuccess(false);
            resultVO.setMsg("块数量不能小于1");
        }
        if (index < 0) {
            resultVO.setSuccess(false);
            resultVO.setMsg("当前块不能小于0");
        }

        if (StringUtils.isEmpty(uploadId) && index > 0) {
            resultVO.setSuccess(false);
            resultVO.setMsg("上传编号为空");
        }

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String fileName = request.getParameter("fileName");
        if (files != null && files.size() > 0) {
            MultipartFile file = files.get(0);
            //首次上传需创建上传编号
            if (StringUtils.isEmpty(uploadId) || uploadId.equals("undefind")) {
                uploadId = generateUploadId();
            }

            //块处理
            //块文件名称,先缓存块儿到本地
            String blockName = uploadId + "_" + index + ".block";
            String folerPath = commonConfig.getLocalFilePath() + "/" + fileName + "/";
            File folder = new File(folerPath);
            File fileToCache = FileUtil.createFile(folerPath + blockName);
            FileUtil.writeFile(fileToCache, file.getBytes());

            resultVO.setSuccess(true);
            resultVO.setMsg("保存成功");
            resultVO.setUploadId(uploadId);
            resultVO.setResultData("0");//说明部分上传成功

            //如果是最后一块
            //判断块文件是否已将上传完，上传完合并文件
            List<File> allblocks = FileUtil.allFiles(folder, ".block");
            List<File> blocks = new ArrayList();
            for (File tf : allblocks) {
                if (tf.getName().contains(uploadId)) {
                    blocks.add(tf);
                }
            }

            if (blocks.size() >= total) {//是最后一块
                String newFileName = folerPath + fileName;
                for (int i = 0; i < total; i++) {
//                    File blockPathFile = new File(folerPath + uploadId + "_" + i + ".block");
                    //写到一个文件
                    File ff = new File(folerPath + uploadId + "_" + i + ".block");
                    FileInputStream fi = new FileInputStream(ff);
                    FileUtil.save(fi, newFileName);//合并为一个文件
                    ff.delete();
                }

                //上传到资源服务器
                SftpUtils sftpUtils = new SftpUtils(nginxConfig.getUrl(), nginxConfig.getPort(), nginxConfig.getUser()
                        , nginxConfig.getPass());
                File nff = new File(newFileName);
                FileInputStream newFileInputStream = new FileInputStream(nff);
                sftpUtils.upload(nginxConfig.getPathApk() + "/" + path, newFileInputStream, fileName);
                newFileInputStream.close();
                nff.delete();

                resultVO.setResultData("1");//如果为1说明全部上传成功
            }
        } else {
            resultVO.setSuccess(false);
            resultVO.setMsg("文件不能为空");
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
