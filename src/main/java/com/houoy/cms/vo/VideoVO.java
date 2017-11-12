package com.houoy.cms.vo;

import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VideoVO extends SuperVO {
    @ApiModelProperty(hidden = true)
    private String pk_video;
    @ApiModelProperty(value = "视频编码",hidden = false)
    private String video_code;
    @ApiModelProperty(value = "视频名称",hidden = false)
    private String video_name;
    @ApiModelProperty(value = "视频详细信息",hidden = false)
    private String video_desc;
    @ApiModelProperty(value = "视频时长（秒）",hidden = false)
    private String video_length;
    @ApiModelProperty(value = "人物单只手臂活动次数(次)",hidden = false)
    private String actor_times;
    @ApiModelProperty(value = "人物消耗卡路里大概值(cal)",hidden = false)
    private String actor_calorie;
    @ApiModelProperty(value = "缩略图路径",hidden = false)
    private String path_thumbnail;
    @ApiModelProperty(value = "视频路径",hidden = false)
    private String path;
    @ApiModelProperty(hidden = true)
    private String pk_folder;

    @Override
    public String getPKField() {
        return "pk_video";
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






