package cn.lhx.dishsys.controller;

import cn.lhx.dishsys.core.base.JsonResult;
import cn.lhx.dishsys.core.enmus.ResultCode;
import cn.lhx.dishsys.entity.UserInfo;
import cn.lhx.dishsys.service.UserService;
import cn.lhx.dishsys.util.JwtUtil;
import cn.lhx.dishsys.util.Md5Util;
import cn.lhx.dishsys.util.RedisUtil;
import com.wf.captcha.GifCaptcha;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lee549
 * @date 2020/5/21 22:51
 */
@RestController
public class LoginController {
    @Resource
    UserService userService;

    @RequestMapping("/auth/login")
    public JsonResult<Object> login(String verKey, String verCode,
                                    @RequestParam("userAccount") String username,
                                    @RequestParam("userPass") String password) {
        String redisCode = (String) RedisUtil.get(verKey);
        if (verCode == null || !redisCode.equals(verCode.trim().toLowerCase())) {
            return JsonResult.error("输入的验证不正确！");
        }
        UserInfo userInfo = userService.selectByName(username);
        //查询数据库判断密码是否相等
        if (userInfo.getUserPass().equals(Md5Util.encode(password,userInfo.getUserId().toString()))) {
            HashMap<Object, Object> data = new HashMap<>(2);
            data.put("token", JwtUtil.sign(username,userInfo.getUserPass()));
            return JsonResult.success(data, ResultCode.SUCCESS.val);
        } else {
            throw new AuthenticationException();

        }
    }

    @GetMapping("/captcha")
    public JsonResult<Object> captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 使用gif验证码
        GifCaptcha gifCaptcha = new GifCaptcha(130, 48, 4);
        // CaptchaUtil.out(gifCaptcha, request, response);
        String verCode = gifCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        RedisUtil.set(key, verCode, 5, TimeUnit.MINUTES);
        HashMap<Object, Object> res = new HashMap<>(2);
        res.put("key", key);
        res.put("image", gifCaptcha.toBase64());
        return JsonResult.success(res);

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
