package com.hong.cummunity.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class GlobalException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object o,
                                         Exception e) {
        ModelAndView mv = new ModelAndView();

        if (e instanceof RuntimeException) {
            mv.addObject("errorMessage", e.getMessage());
        } else {
            mv.addObject("errorMessage", "出现了未知错误，请联系管理员。");
        }
        mv.setViewName("error");
        return mv;
    }
}
