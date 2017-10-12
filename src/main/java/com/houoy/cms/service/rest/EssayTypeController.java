package com.houoy.cms.service.rest;

import com.houoy.cms.service.EssayTypeService;
import com.houoy.cms.vo.EssayTypeVO;
import com.houoy.common.vo.RequestResultVO;
import com.houoy.common.vo.TreeVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章类型目录管理
 * Created by andyzhao on 2017/5/2.
 */
@RestController
@RequestMapping("/api/essaytype")
public class EssayTypeController {

    @Resource
    private EssayTypeService essayTypeService;

    @ResponseBody
    @RequestMapping("/save")
    public RequestResultVO add(@RequestBody EssayTypeVO vo) {
        Integer num = 0;
        RequestResultVO resultVO = new RequestResultVO();
        if (vo != null) {
            if (vo.getPk_type() != null) {//如果前端传递过来pk,则判断为更新操作
                num = essayTypeService.updateByVO(vo);
            } else {
                num = essayTypeService.saveByVO(vo);
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
        List<EssayTypeVO> essayTypeVOs = essayTypeService.retrieveByParentPK(pks);
        if (essayTypeVOs != null && essayTypeVOs.size() > 0) {
            resultVO.setSuccess(false);
            resultVO.setMsg("此目录有子节点无法删除.");
        } else {
            //TODO 有图片无法删除
            Integer num = essayTypeService.deleteByPK(pks);
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
        List<EssayTypeVO> essayTypeVOs = essayTypeService.retrieveAll();
        TreeVO tree = TreeVO.listToTreeNode(essayTypeVOs);
        RequestResultVO resultVO = new RequestResultVO();
        if (tree != null) {
            resultVO.setSuccess(true);
            resultVO.setMsg("查询成功");
            resultVO.setResultData(tree);
        } else {
            resultVO.setSuccess(false);
            resultVO.setMsg("数据为空");
        }

        return resultVO;
    }

}


