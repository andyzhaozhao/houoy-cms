package com.houoy.cms.vo;

import com.houoy.common.vo.SuperVO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图片管理
 */
@Data
@NoArgsConstructor
public class ImageVO extends SuperVO {
    private String pk_image;
    private String image_code;
    private String image_name;
    private String path;
    private String pk_folder;

    @Override
    public String getPKField() {
        return null;
    }

    @Override
    public String getTableName() {
        return "cms_image";
    }

    @Override
    public Object getPKValue() {
        return pk_image;
    }
}






