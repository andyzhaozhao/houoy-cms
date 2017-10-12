package com.houoy.cms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目配置
 * Created by andyzhao on 2017-02-21.
 */
@Component
@ConfigurationProperties(prefix = "cms")
public class CommonConfig {
    //读取application配置文件中的变量
    private String houoy;

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHouoy() {
        return houoy;
    }

    public void setHouoy(String houoy) {
        this.houoy = houoy;
    }
}
