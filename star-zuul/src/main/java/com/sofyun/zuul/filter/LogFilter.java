package com.sofyun.zuul.filter;

import cn.hutool.core.date.DateUtil;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sofyun.common.model.LogModel;
import com.sofyun.zuul.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Enumeration;

/**
 * @ClassName LogFilter
 * @Description TODO
 * @Author gm
 * @Date 2019/3/12 17:16
 **/
@Slf4j
@Component
public class LogFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return  FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 2;
    }

    @Override
    public boolean shouldFilter() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        String auth = request.getHeader("Authorization");
//        if (StringUtils.isBlank(auth) || auth.contains("Basic")){
//            return false;
//        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        LogModel logModel = new LogModel();
        logModel.setIp(IPUtil.getIpAddr(request));
        logModel.setMethod(request.getMethod());
        logModel.setPath(request.getServletPath());
        logModel.setRequestTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
        ServletInputStream inp = null;
        try {
            Enumeration<String> names = request.getParameterNames();
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            while (names.hasMoreElements()) {
                String key = names.nextElement();
                sb.append("[").append(key).append("=").append(request.getParameter(key)).append("]");
            }
            sb.append("}");
            logModel.setParams(sb.toString());
            inp = request.getInputStream();
            String boby = IOUtils.toString(inp, Charset.forName("UTF-8"));
            logModel.setBody(boby);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert inp != null;
                inp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        InputStream out = null;
        try {
            out = ctx.getResponseDataStream();
            String outBody = StreamUtils.copyToString(out, Charset.forName("UTF-8"));
            ctx.setResponseBody(outBody);
            logModel.setOutBody(outBody);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info(logModel.toString());
        return null;
    }



}
