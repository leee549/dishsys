package cn.lhx.dishsys.controller;

import cn.lhx.dishsys.core.base.JsonResult;
import cn.lhx.dishsys.core.enmus.ResultCode;
import cn.lhx.dishsys.entity.DishesInfo;
import cn.lhx.dishsys.service.DishesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lee549
 * @date 2020/6/9 12:55
 */
@RestController
public class DishesController {
    @Resource private DishesService dishesService;

    @GetMapping("/show")
    public JsonResult<Object> showDishes(){
        QueryWrapper<DishesInfo> qw = new QueryWrapper<>();
        qw.lambda().eq(DishesInfo::getDishesName,"展示");
        List<DishesInfo> list = dishesService.list(qw);
        return JsonResult.success(list, ResultCode.SUCCESS.val);
    }
    @GetMapping("/list")
    public JsonResult<Object> list(){
        List<DishesInfo> list = dishesService.list();
        return JsonResult.success(list, ResultCode.SUCCESS.val);
    }


}
