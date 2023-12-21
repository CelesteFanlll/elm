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
public class Shop implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "shop_id", type = IdType.AUTO)
    private Integer shopId;
    private String shopName;
    private Integer businessId;
    private String shopImg;
    private String shopDes;
    private String shopLoc;
    private double startPrice;
    private double delPrice;
    private String tag;

}
