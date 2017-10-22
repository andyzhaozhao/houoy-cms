package com.houoy.cms.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.MultipartConfigElement;

/**
 * 项目配置
 * Created by andyzhao on 2017-02-21.
 */
@Component
@ConfigurationProperties(prefix = "cms")
@Data
@NoArgsConstructor
public class CommonConfig {
    //读取application配置文件中的变量
    private String houoy;

    private String date;

    private String localFilePath;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(1024L * 1024L* 1024l* 1024l);
        return factory.createMultipartConfig();
    }
}
