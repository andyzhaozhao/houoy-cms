package com.houoy.cms.vo;

import com.houoy.common.vo.SuperVO;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 此类没有使用getter和setter方法，保持简洁
 */
@Data
@NoArgsConstructor
public class EssayVO extends SuperVO {

    private String pk_essay;
    private String essay_code;
    private String essay_name;
    private String essay_subname;
    private String essay_content;

    private Integer is_publish;//>1是，=0否
    private String ts_start;//活动开始时间
    private String ts_end;//活动结束时间
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






