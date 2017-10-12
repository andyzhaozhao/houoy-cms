package com.houoy.cms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 资源服务器地址
 * Created by andyzhao on 2017-02-21.
 */
@Component
@ConfigurationProperties(prefix = "nginx")
public class NginxConfig {
    //读取application配置文件中的变量
    private String url;
    private Integer port;
    private String user;
    private String pass;
    private String path;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
