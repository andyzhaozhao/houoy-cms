package com.houoy.cms.vo;

import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApkVO extends SuperVO {
    @ApiModelProperty(hidden = true)
    private String pk_apk;
    @ApiModelProperty(value = "最新安装包版本号", hidden = false)
    private Integer newestVersionCode;
    @ApiModelProperty(value = "最新安装包版本名称", hidden = false)
    private String newestVersionName;
    @ApiModelProperty(value = "安装包url", hidden = false)
    private String url;
    @ApiModelProperty(value = "安装包大小", hidden = false)
    private String size;
    @ApiModelProperty(value = "版本说明", hidden = false)
    private String comment;
    @ApiModelProperty(hidden = true)
    private String pk_folder;

    @Override
    public String getPKField() {
        return "pk_apk";
    }

    @Override
    public String getTableName() {
        return "cms_apk";
    }

    @Override
    public Object getPKValue() {
        return pk_apk;
    }
}






