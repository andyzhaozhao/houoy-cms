package com.houoy.cms.vo;

import com.houoy.common.vo.TreeVO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章分类目录管理
 */
@Data
@NoArgsConstructor
public class EssayTypeVO extends TreeVO<EssayTypeVO> {
    private String pk_type;
    private String type_code;
    private String type_name;
    private String type_desc;
    private String pk_parent;

    //冗余字段
    private String text;

    @Override
    public String getPKField() {
        return "pk_type";
    }

    @Override
    public String getTableName() {
        return "cms_essay_type";
    }

    @Override
    public String getParentPKField() {
        return "pk_parent";
    }

    @Override
    public Object getPKValue() {
        return pk_type;
    }

}
