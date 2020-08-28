package cn.lhx.dishsys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("user_info")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Integer userId;
    private String userAccount;
    private String userPass;
    private Integer roleId;
    private Integer locked;
    private String faceImg;
}
