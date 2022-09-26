package com.hong.cummunity.adivce;

import com.alibaba.fastjson.JSON;
import com.hong.cummunity.dto.ResultDTO;
import com.hong.cummunity.exception.CustomizeErrorCode;
import com.hong.cummunity.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Model model, Throwable e,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        String contentType = request.getContentType();

        if (contentType.equals("application/json")) {
            ResultDTO resultDTO;
            //返回json
            if (e instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
            }
            PrintWriter writer = null;
            try {
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
            } catch (IOException ex) {
            } finally {
                writer.flush();
                writer.close();
            }

            return null;
        } else {
            //返回error页面
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SERVER_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }


}
