package cn.lhx.dishsys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author lee549
 * @date 2020/5/28 20:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("menu")

public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Integer parentId;
    private Integer level;
    private String url;
    private Integer state;
    private String icon;
    /**
     * 排序字段 关键字段要加``
     */
    @TableField(value = "`order`")
    private Integer order;

    @TableField(exist = false)
    private List<Menu> childMenus;
}
