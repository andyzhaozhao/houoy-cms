package com.houoy.cms.vo;

import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 此类没有使用getter和setter方法，保持简洁
 */
@Data
@NoArgsConstructor
public class EssayVO extends SuperVO {
    @ApiModelProperty(required = false,hidden = true)
    private String pk_essay;
    @ApiModelProperty(value = "编码", example = "name",hidden = false)
    private String essay_code;
    @ApiModelProperty(value = "名称", example = "name",hidden = false)
    private String essay_name;

    @ApiModelProperty(value = "副名称", example = "name",hidden = false)
    private String essay_subname;
    @ApiModelProperty(value = "内容", example = "name",hidden = false)
    private String essay_content;

    @ApiModelProperty(value = "是否发布（1是，=0否）", example = "name",hidden = false)
    private Integer is_publish;//>1是，=0否
    @ApiModelProperty(value = "开始时间", example = "name",hidden = false)
    private String ts_start;//活动开始时间
    @ApiModelProperty(value = "结束时间", example = "name",hidden = false)
    private String ts_end;//活动结束时间
    @ApiModelProperty(required = false,hidden = true)
    private String pk_type;//属于哪个类型


    @Override
    public String getPKField() {
        return null;
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public Object getPKValue() {
        return pk_essay;
    }
}






