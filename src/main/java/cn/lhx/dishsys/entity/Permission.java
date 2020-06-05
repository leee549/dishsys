package cn.lhx.dishsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lee549
 * @date 2020/5/30 20:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String expression;

}
