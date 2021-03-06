package cn.lhx.dishsys.core.exception;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.lhx.dishsys.core.base.JsonResult;
import cn.lhx.dishsys.core.enmus.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lee549
 * @date 2020/4/9 10:47
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
  Map<String, Object> map = new LinkedHashMap<>();

  /** 未知账号 */
  @ResponseBody
  @ExceptionHandler(UnknownAccountException.class)
  public JsonResult<Object> unknownAccountException(UnknownAccountException ex) {
    map.put("data", ex);
    return JsonResult.error(ResultCode.ACC_NOT_EXIST.msg, map);
  }
  /** 密码错误 */
  @ResponseBody
  @ExceptionHandler(IncorrectCredentialsException.class)
  public JsonResult<Object> incorrectCredentialsException(IncorrectCredentialsException ex) {
    map.put("data", ex);
    return JsonResult.error(ResultCode.PWD_NOT_EXIST.msg, map);
  }
  /** 账号或密码错误 */
  @ResponseBody
  @ExceptionHandler(AuthenticationException.class)
  public JsonResult<Object> authenticationException(AuthenticationException ex) {
    map.put("data", ex);
    return JsonResult.error(ResultCode.NOT_EXIST.msg, map);
  }

  /** 未授权 */
  @ExceptionHandler(UnauthorizedException.class)
  public String unauthorizedException(UnauthorizedException ex, HttpServletRequest request) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("exception", ex);
    mv.addObject("url", request.getRequestURL());
    request.setAttribute("ext", mv);
    request.setAttribute("javax.servlet.error.status_code", ResultCode.UN_AUTHORIZED.val);
    return "forward:/error";
  }

  /** 未认证 */
  @ResponseBody
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  @ExceptionHandler(UnauthenticatedException.class)
  public JsonResult<Object> unauthorizedException(UnauthenticatedException ex){
    log.error("身份未认证",ex);
    return JsonResult.error(ResultCode.UN_AUTHENTICATED.msg,ResultCode.UN_AUTHENTICATED.val);
  }


  /** 未知异常 */
  @ExceptionHandler(Exception.class)
  public String unkownException(Exception ex) {
    map.put("data", ex);
    log.error("未知异常:{}", ExceptionUtil.stacktraceToString(ex));
    return "forward:/error";
  }
}
