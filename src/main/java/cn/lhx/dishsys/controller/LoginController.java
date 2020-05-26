package cn.lhx.dishsys.controller;

import cn.lhx.dishsys.core.base.JsonResult;
import cn.lhx.dishsys.core.enmus.ResultCode;
import cn.lhx.dishsys.entity.UserInfo;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author lee549
 * @date 2020/5/21 22:51
 */
@Controller
public class LoginController {

    // @GetMapping("/login")
    // public String toLogin(){
    //     return "login";
    // }
    // @ResponseBody
    // @RequestMapping("/")
    // public String login(){
    //     return "";
    // }

    @RequestMapping("/test")
    public String test(){
        return "starter";
    }
    @ResponseBody
    @RequestMapping("/auth/login")
    public JsonResult<Object> login(String captcha , UserInfo userInfo, HttpServletRequest request){
        if (!CaptchaUtil.ver(captcha, request)) {
            System.out.println(request.getSession().getAttribute("captcha") + "==========");
            // 清除session中的验证码
            CaptchaUtil.clear(request);
            return JsonResult.error("输入的验证不正确！",null);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token =
                new UsernamePasswordToken(userInfo.getUserAccount(), userInfo.getUserPass(), false);
        subject.login(token);
        HashMap<Object, Object> data = new HashMap<>(2);
        data.put("token",userInfo.getUserAccount());
        return JsonResult.success(data, ResultCode.SUCCESS.val);
    }



    @ResponseBody
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 使用gif验证码
        GifCaptcha gifCaptcha = new GifCaptcha(130, 48, 4);
        CaptchaUtil.out(gifCaptcha, request, response);
        // 验证码存入session
        // System.out.println(request.getSession());
        // System.out.println(request.getSession().getAttribute("captcha"));
        // request.getSession().setAttribute("captcha", gifCaptcha.text().toLowerCase());
        // 中文类型
        //        ChineseCaptcha captcha = new ChineseCaptcha(130, 48);
        //        CaptchaUtil.out(captcha,request,response);

        // 中文gif类型
        //        ChineseGifCaptcha captcha = new ChineseGifCaptcha(130, 48);
        //        CaptchaUtil.out(captcha,request,response);
        // 算术类型
        //        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        //        captcha.setLen(3);  // 几位数运算，默认是两位
        //        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
        //        captcha.text();  // 获取运算的结果：5
        //
        //        CaptchaUtil.out(captcha,request, response);
    }
}
