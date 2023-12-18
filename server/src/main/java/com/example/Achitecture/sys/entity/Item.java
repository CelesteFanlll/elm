package com.example.Achitecture.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@Data
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "item_id", type = IdType.AUTO)
    private Integer itemId;

    private String itemName;

    private String itemDescripition;

    private Double itemPrice;

    private Integer shopId;

    private Integer isListed;

    private String itemImg;


}
