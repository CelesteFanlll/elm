package com.example.Achitecture.sys.service;

import com.example.Achitecture.common.DTO.TClassDTO;
import com.example.Achitecture.sys.entity.Customer;
import com.example.Achitecture.sys.entity.COrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzq
 * @since 2023-12-02
 */
public interface IOrderService extends IService<COrder> {

    List<TClassDTO> searchOrder(Customer customer);

    int pay(COrder cOrder);

    int refund(COrder cOrder);

    int getid(COrder cOrder);
}
