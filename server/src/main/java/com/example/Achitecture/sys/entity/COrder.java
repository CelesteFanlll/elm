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
@Data
@TableName("COrder")
public class COrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    private String customerName;

    private Integer orderStatus;

    private Double totalAmount;

    private Integer shopId;


}
