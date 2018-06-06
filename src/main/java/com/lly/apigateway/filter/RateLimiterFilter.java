package com.lly.apigateway.filter;


import com.google.common.util.concurrent.RateLimiter;
import com.lly.apigateway.exception.RateLimiterException;
import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 限流
 */
@Component
public class RateLimiterFilter extends ZuulFilter
{

    //    令牌桶算法 实现限流的作用
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType()
    {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder()
    {
//SERVLET_DETECTION_FILTER_ORDER 优先级是-3 是最该的 限流filter 要在其之前
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter()
    {
        return true;
    }

    @Override
    public Object run()
    {

        if (!RATE_LIMITER.tryAcquire())
        {
            throw new RateLimiterException();
        }
        return null;
    }
}
