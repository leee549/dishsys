package cn.lhx.dishsys.service;

import cn.lhx.dishsys.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author lee549
 * @date 2020/5/23 20:34
 */
public interface UserService extends IService<UserInfo> {
    UserInfo selectByName(String username);

}
