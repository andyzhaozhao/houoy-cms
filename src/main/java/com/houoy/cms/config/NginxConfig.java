package com.houoy.cms.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 资源服务器地址
 * Created by andyzhao on 2017-02-21.
 */
@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "nginx")
public class NginxConfig {
    //读取application配置文件中的变量
    private String url;
    private Integer port;
    private String user;
    private String pass;
    private String path;
    private String pathVideo;
    private String pathApk;
}
