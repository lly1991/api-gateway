package com.lly.apigateway.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * token 过滤器
 */
@Component
public class TokenFilter extends ZuulFilter
{

    @Override
    public String filterType()
    {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder()
    {
        return PRE_DECORATION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter()
    {
        return true;
    }

    @Override
    public Object run()
    {

        //拿到当前上下文对象
        RequestContext currentContext = RequestContext.getCurrentContext();
//        拿到httprequest
        HttpServletRequest request = currentContext.getRequest();
//        查看校验参数token
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
