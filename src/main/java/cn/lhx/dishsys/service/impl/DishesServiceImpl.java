package cn.lhx.dishsys.service.impl;

import cn.lhx.dishsys.dao.DishesDao;
import cn.lhx.dishsys.entity.DishesInfo;
import cn.lhx.dishsys.service.DishesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author lee549
 * @date 2020/6/9 12:55
 */
@Service
public class DishesServiceImpl extends ServiceImpl<DishesDao, DishesInfo> implements DishesService {
}
