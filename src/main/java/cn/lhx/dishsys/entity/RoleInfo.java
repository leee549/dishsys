package cn.lhx.dishsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**UserInfo
 * @author lee549
 * @date 2020/5/21 21:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role_info")
public class RoleInfo implements Serializable {
    private Integer roleId;
    private String roleName;

}
