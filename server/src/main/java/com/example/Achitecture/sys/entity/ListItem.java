package com.example.Achitecture.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzq
 * @since 2023-12-02
 */
@TableName("list_item")
@Data
public class ListItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "list_item_id", type = IdType.AUTO)
    private Integer listItemId;

    private Integer orderId;

    private Integer itemId;

    private String quantity;

    private Double itemAmount;


}
