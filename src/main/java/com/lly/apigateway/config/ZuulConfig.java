package com.lly.apigateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

@ConfigurationProperties("zuul")
@RefreshScope
public class ZuulConfig
{

    public ZuulProperties zuulProperties(){

        return  new ZuulProperties();
    }

}
