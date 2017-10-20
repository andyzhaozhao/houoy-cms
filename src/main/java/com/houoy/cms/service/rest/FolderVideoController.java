package com.houoy.cms.service.rest;

import com.houoy.cms.service.FolderService;
import com.houoy.cms.service.FolderVideoService;
import com.houoy.cms.vo.FolderVO;
import com.houoy.common.vo.RequestResultVO;
import com.houoy.common.vo.TreeVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频资源目录管理
 * Created by andyzhao on 2017/5/2.
 */
@RestController
@RequestMapping("/api/folderVideo")
public class FolderVideoController {

    @Resource
    private FolderVideoService folderVideoService;

    @ResponseBody
    @RequestMapping("/save")
    public RequestResultVO add(@RequestBody FolderVO vo) {
        Integer num = 0;
        RequestResultVO resultVO = new RequestResultVO();
        if (vo != null) {
            if (vo.getPk_folder() != null) {//如果前端传递过来pk,则判断为更新操作
                num = folderVideoService.updateByVO(vo);
            } else {
                num = folderVideoService.saveByVO(vo);
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

    @ResponseBody
    @RequestMapping("/delete")
    public RequestResultVO delete(@RequestBody List<String> pks) {
        RequestResultVO resultVO = new RequestResultVO();
        List<FolderVO> folderVOs = folderVideoService.retrieveByParentPK(pks);
        if(folderVOs!=null && folderVOs.size()>0){
            resultVO.setSuccess(false);
            resultVO.setMsg("此目录有子节点无法删除.");
        }else{
            //TODO 有图片无法删除
            Integer num = folderVideoService.deleteByPK(pks);
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

    @ResponseBody
    @RequestMapping(value = "retrieve")
    public RequestResultVO retrieve() throws Exception {
        List<FolderVO> folderVOs = folderVideoService.retrieveAll();
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

}


