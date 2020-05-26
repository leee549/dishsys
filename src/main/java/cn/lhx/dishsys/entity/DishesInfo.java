package cn.lhx.dishsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * UserInfo
 *
 * @author lee549
 * @date 2020/5/21 21:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dishes_info")
public class DishesInfo implements Serializable {
    private Integer dishesId;
    private String dishesName;
    private String dishesDiscript;
    private String dishesImg;
    private String dishesTxt;
    private Integer recommend;
    private Float dishesPrice;


}
