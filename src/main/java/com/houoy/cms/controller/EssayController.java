package com.houoy.cms.controller;

import com.houoy.cms.config.CommonConfig;
import com.houoy.cms.service.EssayService;
import com.houoy.cms.vo.EssayVO;
import com.houoy.common.utils.JqueryDataTablesUtil;
import com.houoy.common.vo.JquryDataTablesVO;
import com.houoy.common.vo.RequestResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by andyzhao on 2017-02-20.
 */
@RestController
@RequestMapping("/api/essay")
public class EssayController {

    @Autowired
    private CommonConfig commonConfig;

    @Autowired
    private EssayService essayService;

    @ResponseBody
    @RequestMapping("/save")
    public RequestResultVO essayAdd(@RequestBody EssayVO essayVO) {
        Integer num = null;
        RequestResultVO resultVO = new RequestResultVO();
        if (essayVO != null) {
            if (essayVO.getPk_essay() != null) {//如果前端传递过来pk,则判断为更新操作
                num = essayService.updateByVO(essayVO);
            } else {
                num = essayService.saveByVO(essayVO);
            }
            if (num != null) {
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
    public RequestResultVO deletes(@RequestBody List<String> _ids) {
        RequestResultVO resultVO = new RequestResultVO();
        if (_ids != null) {
            essayService.deleteByPK(_ids);
            resultVO.setSuccess(true);
            resultVO.setMsg("删除");
            resultVO.setResultData(_ids.size());

        } else {
            resultVO.setSuccess(false);
            resultVO.setMsg("参数不可为null");
        }
        return resultVO;
    }

    @ResponseBody
    @RequestMapping(value = "/retrieve")
    public JquryDataTablesVO<EssayVO> retrieve(EssayVO vo, HttpServletRequest request) {
        vo = (EssayVO) JqueryDataTablesUtil.filterParam(vo, request);
//        List<EssayVO> result = essayService.findAll();
//        Long count = essayService.count();
        List<EssayVO> result = essayService.retrieveAllWithPage(vo);
        Long count = essayService.retrieveAllCount();

        JquryDataTablesVO rtv = JqueryDataTablesUtil.madeJqueryDatatablesVO(count, result);
        return rtv;
    }

    @ResponseBody
    @RequestMapping(value = "/retrieveByPk")
    public RequestResultVO retrieve(String pk_essay) {
        List<EssayVO> result = essayService.retrieveByPK(pk_essay);
        RequestResultVO resultVO = new RequestResultVO();
        resultVO.setSuccess(true);
        resultVO.setMsg("获取");
        resultVO.setResultData(result.get(0));
        return resultVO;
    }
}


