package com.houoy.cms.vo;

import com.houoy.common.vo.TreeVO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源目录管理
 */
@Data
@NoArgsConstructor
public class FolderVO extends TreeVO<FolderVO> {
    private String pk_folder;
    private String folder_code;
    private String folder_name;
    private String pk_parent;

    //冗余字段
    private String text;

    @Override
    public String getPKField() {
        return "pk_folder";
    }

    @Override
    public String getTableName() {
        return "cms_folder";
    }

    @Override
    public String getParentPKField() {
        return "pk_parent";
    }

    @Override
    public Object getPKValue() {
        return pk_folder;
    }

}
