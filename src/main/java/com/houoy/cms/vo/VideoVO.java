package com.houoy.cms.vo;

import com.houoy.common.vo.SuperVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VideoVO extends SuperVO {
    private String pk_video;
    private String video_code;
    private String video_name;
    private String video_desc;
    private String path;
    private String pk_folder;

    @Override
    public String getPKField() {
        return null;
    }

    @Override
    public String getTableName() {
        return "cms_video";
    }

    @Override
    public Object getPKValue() {
        return pk_video;
    }
}






