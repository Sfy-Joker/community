package com.hong.cummunity.adivce;

import com.hong.cummunity.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomizeExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Model model, Throwable e) {
        if (e instanceof CustomizeException) {
            model.addAttribute("message", e.getMessage());
        } else {
            model.addAttribute("message", "服务器冒烟了，要不然你稍后再试试！！");
        }
        return new ModelAndView("error");
    }


}
