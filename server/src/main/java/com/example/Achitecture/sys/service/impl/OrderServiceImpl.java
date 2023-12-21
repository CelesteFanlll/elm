package com.example.Achitecture.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.Achitecture.common.Factory.OrderFactory;
import com.example.Achitecture.common.BaseAbstractClass.AbstractFactory;
import com.example.Achitecture.common.DTO.TClassDTO;
import com.example.Achitecture.sys.entity.Customer;
import com.example.Achitecture.sys.entity.COrder;
import com.example.Achitecture.sys.entity.Shop;
import com.example.Achitecture.sys.mapper.OrderMapper;
import com.example.Achitecture.sys.mapper.ShopMapper;
import com.example.Achitecture.sys.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

;import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzq
 * @since 2023-12-02
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, COrder> implements IOrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ShopMapper shopMapper;

    AbstractFactory<COrder> orderAbstractFactory = new OrderFactory();
    COrder cOrderNew = orderAbstractFactory.createEntity();

    @Override
    public List<TClassDTO> searchOrder(Customer customer) {
        List<TClassDTO> result = new ArrayList<>();
        LambdaQueryWrapper<COrder> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(COrder::getCustomerName, customer.getCustomerName());
        List<COrder> orders = orderMapper.selectList(queryWrapper1);
        for (COrder order : orders) {
            TClassDTO dto = new TClassDTO();
            dto.setCOrder(order);
            LambdaQueryWrapper<Shop> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(Shop::getShopId, order.getShopId());
            Shop shop = shopMapper.selectOne(queryWrapper2);
            dto.setShop(shop);
            result.add(dto);
        }
        return result;
    }

    @Override
    public int pay(COrder cOrder) {

        cOrderNew.setOrderId(cOrder.getOrderId());
        cOrderNew.setOrderStatus(1);
        return orderMapper.updateById(cOrderNew);
    }

    @Override
    public int refund(COrder cOrder) {
        cOrderNew.setOrderId(cOrder.getOrderId());
        cOrderNew.setOrderStatus(2);
        return orderMapper.updateById(cOrderNew);
    }

    @Override
    public int getid(COrder cOrder) {
        orderMapper.insert(cOrder);
        return cOrder.getOrderId();
    }


}

