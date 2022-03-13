package com.xuan.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    //解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest request) {

        String language = request.getParameter("l");//获得请求中的语言参数

        Locale locale = Locale.getDefault();//默认的，没有使用

        //如果请求的语言参数携带了请求的地区，
        if(!StringUtils.isEmpty(language)){
            String[] split = language.split("_");
            //国家地区
            locale = new Locale(split[0], split[1]);


        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
