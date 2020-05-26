package cn.lhx.dishsys.service.impl;

import cn.lhx.dishsys.dao.UserDao;
import cn.lhx.dishsys.entity.UserInfo;
import cn.lhx.dishsys.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lee549
 * @date 2020/5/23 20:34
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao,UserInfo> implements UserService {
    @Resource private UserDao userDao;

    @Override
    public UserInfo selectByName(String username) {
        QueryWrapper<UserInfo> qw = new QueryWrapper<>();
        qw.lambda().eq(UserInfo::getUserAccount,username);
        return userDao.selectOne(qw);
    }
}
