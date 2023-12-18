package com.example.Achitecture.sys.controller;

import com.example.Achitecture.common.BaseAbstractClass.BaseController;
import com.example.Achitecture.common.DTO.Result;
import com.example.Achitecture.common.DTO.TClassDTO;
import com.example.Achitecture.sys.entity.Customer;
import com.example.Achitecture.sys.entity.COrder;
import com.example.Achitecture.sys.mapper.OrderMapper;
import com.example.Achitecture.sys.service.IOrderService;
import com.example.Achitecture.sys.service.impl.OrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-12-02
 */
@RestController
@RequestMapping("/sys/order")
@Api(tags = "订单控制器")
public class OrderController extends BaseController<OrderServiceImpl, OrderMapper, COrder,Long> {

    public OrderController(OrderServiceImpl ls) {
        super(ls);
    }

    @Autowired
    IOrderService orderService;


    @ApiOperation("通过顾客姓名查询该顾客的订单")
    @PostMapping("/search")
    public Result<?> searchOrder(@RequestBody Customer customer){
        List<TClassDTO> data = orderService.searchOrder(customer);
        return Result.success(data);

    }

    @ApiOperation("模拟支付")
    @PostMapping("/pay")
    public int pay(@RequestBody COrder cOrder){
        return orderService.pay(cOrder);
    }


    @ApiOperation("模拟退款")
    @PostMapping("/refund")
    public int refund(@RequestBody COrder cOrder){
        return orderService.refund(cOrder);
    }

    @ApiOperation("生成订单并返回订单号")
    @PostMapping("/getid")
    public int getid(@RequestBody COrder cOrder){

        return orderService.getid(cOrder);
    }





}
