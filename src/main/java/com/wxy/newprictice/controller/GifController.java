package com.wxy.newprictice.controller;

import com.wxy.newprictice.vcode.Captcha;
import com.wxy.newprictice.vcode.GifCaptcha;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Api(value = "GIF验证码", description = "GIF验证码")
@RestController
public class GifController {
    /**
     * 获取验证码（Gif版本）
     * @param response
     */
    @RequestMapping(value="/getGifCode",method= RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpServletRequest request,HttpSession session){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146,33,4);
            //输出
            captcha.out(response.getOutputStream());
            //存入Session
            session.setAttribute("_code",captcha.text().toLowerCase());
            Object code = session.getAttribute("_code");
            System.out.println(code.toString());
        } catch (Exception e) {
            System.err.println("获取验证码异常："+e.getMessage());
        }
    }
}
