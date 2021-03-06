package com.houoy.cms.service.rest;

import com.houoy.cms.service.FolderApkService;
import com.houoy.cms.vo.ApkVO;
import com.houoy.cms.vo.FolderVO;
import com.houoy.common.vo.PageResultVO;
import com.houoy.common.vo.RequestResultVO;
import com.houoy.common.vo.TreeVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * apk资源目录管理
 * Created by andyzhao on 2017/5/2.
 */
@RestController
@RequestMapping("/api/folderApk")
public class FolderApkController {

    @Resource
    private FolderApkService folderApkService;

    @GetMapping(value = "/check")
    public ApkVO check() {
        ApkVO version = new ApkVO();
        version.setNewestVersionCode(7);
        version.setNewestVersionName("0.6.1");
        version.setUrl("http://192.168.1.28/a.apk");
        version.setSize("4.25M");
        version.setComment("增加更新功能，增加版本验证功能");
        return version;
    }

    @PostMapping("/save")
    public RequestResultVO add(@RequestBody FolderVO vo) {
        Integer num = 0;
        RequestResultVO resultVO = new RequestResultVO();
        if (vo != null) {
            if (vo.getPk_folder() != null) {//如果前端传递过来pk,则判断为更新操作
                num = folderApkService.updateByVO(vo);
            } else {
                num = folderApkService.saveByVO(vo);
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

    @PostMapping("/delete")
    public RequestResultVO delete(@RequestBody List<String> pks) {
        RequestResultVO resultVO = new RequestResultVO();
        List<FolderVO> folderVOs = folderApkService.retrieveByParentPK(pks);
        if (folderVOs != null && folderVOs.size() > 0) {
            resultVO.setSuccess(false);
            resultVO.setMsg("此目录有子节点无法删除.");
        } else {
            //TODO 有图片无法删除
            Integer num = folderApkService.deleteByPK(pks);
            if (num >= 1) {
                resultVO.setSuccess(true);
                resultVO.setMsg("查询成功");
                resultVO.setResultData(num);
            } else {
                resultVO.setSuccess(false);
                resultVO.setMsg("删除失败");
            }
        }
        return resultVO;
    }

    @GetMapping(value = "retrieve")
    public RequestResultVO retrieve() throws Exception {
        List<FolderVO> folderVOs = folderApkService.retrieveAll();
        TreeVO tree = TreeVO.listToTreeNode(folderVOs);
        RequestResultVO resultVO = new RequestResultVO();
        if (tree != null) {
            resultVO.setSuccess(true);
            resultVO.setMsg("查询成功");
            resultVO.setResultData(tree);
        } else {
            resultVO.setSuccess(false);
            resultVO.setMsg("保存");
        }

        return resultVO;
    }

    @ApiOperation(value = "移动端接口，分页获取视频目录列表")
    @GetMapping("/retrieveMobile")
    public PageResultVO retrieveMobile(FolderVO folderVO) {
        List<FolderVO> result = folderApkService.retrieveAllWithPage(folderVO);
        Long count = folderApkService.retrieveAllCount(folderVO);
        PageResultVO pageResultVO = new PageResultVO();
        pageResultVO.setSuccess(true);
        pageResultVO.setMsg("查询成功");
        pageResultVO.setResultData(result);
        pageResultVO.setStart(folderVO.getStart());
        pageResultVO.setLength(folderVO.getLength());
        pageResultVO.setOrderColumnName(folderVO.getOrderColumnName());
        pageResultVO.setOrderDir(folderVO.getOrderDir());
        pageResultVO.setTotal(count);
        return pageResultVO;
    }

}


