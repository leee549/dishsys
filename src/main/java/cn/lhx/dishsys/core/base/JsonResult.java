package cn.lhx.dishsys.core.base;

import cn.lhx.dishsys.core.enmus.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lee549
 * @date 2020/3/25 22:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JsonResult<T> {
    //判断返回成功还是失败
    private Boolean ret;

    // 比如我用ajax 请求查询学生集合，这时候，查到的 List 就是这个T，泛型
    private T data;

    // msg 一般是给失败的放失败的原因，成功不会用到
    private String msg;

    private Integer status;

    /**
     * 成功
     *
     * @return
     */
    public static JsonResult<Object> success() {

        return JsonResult.builder().ret(true).data(null).build();

    }

    /**
     * 成功带数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> JsonResult<Object> success(T data) {
        return JsonResult.builder().ret(true).status(ResultCode.SUCCESS.val).data(data).build();

    }
    public static <T> JsonResult<Object> success(T data,Integer status) {
        return JsonResult.builder().ret(true).status(status).data(data).build();

    }
    public static <T> JsonResult<Object> success(T data,Integer status,String msg) {
        return JsonResult.builder().status(status).data(data).msg(msg).build();

    }

    public static <T> JsonResult<Object> error() {
        return JsonResult.builder().status(ResultCode.fail.val).ret(false).build();

    }

    public static <T> JsonResult<Object> error(String msg) {
        return JsonResult.builder().ret(false).msg(msg).build();
    }

    public static <T> JsonResult<Object> error(String msg,Object data) {
        return JsonResult.builder().ret(false).msg(msg).data(data).build();
    }
//    public JsonResult(String msg,T data){
//        this.msg=msg;
//        this.data=data;
//    }
//    public static <T> JsonResult<T> test(String msg,T data){
//        return new JsonResult<T>(msg,data);
//    }
}
