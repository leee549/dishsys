package cn.lhx.dishsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**UserInfo
 * @author lee549
 * @date 2020/5/21 21:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_info")
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer orderId;
    private Date orderBeginDate;
    private Date orderEndDate;
    private Integer waitedId;
    private Integer orderState;
    private Integer tableId;
}
