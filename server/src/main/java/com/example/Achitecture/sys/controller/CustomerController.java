package com.example.Achitecture.sys.controller;

import com.example.Achitecture.common.BaseAbstractClass.BaseController;
import com.example.Achitecture.common.DTO.Result;
import com.example.Achitecture.sys.entity.Customer;
import com.example.Achitecture.sys.mapper.CustomerMapper;
import com.example.Achitecture.sys.service.ICustomerService;
import com.example.Achitecture.sys.service.impl.CustomerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-12-02
 */
@Api(tags = "顾客的控制器")
@RestController
@RequestMapping("/sys/customer")
public class CustomerController extends BaseController<CustomerServiceImpl, CustomerMapper, Customer,Long> {

    public CustomerController(CustomerServiceImpl ls) {
        super(ls);
    }

    @Autowired
    ICustomerService customerService;

    @ApiOperation("登录功能")
    @PostMapping("/login")
    public Result<?> login(@RequestBody Customer customer){
        Map<String, Object> data = customerService.login(customer);

        if (data != null) {
            return Result.success(data);
        }

        return Result.fail(20001, "用户或密码错误");

    }


    @ApiOperation("顾客注册")
    @PostMapping("/signup")
    public boolean signup(@RequestBody Customer customer){

        return customerService.signup(customer);

    }


    @ApiOperation("查询收货地址")
    @PostMapping("/searchLoc")
    public String searchLoc(@RequestParam String name){

        return customerService.searchLoc(name);
    }

    @ApiOperation("更改收货地址")
    @PostMapping("/changeLoc")
    public Integer changeLoc(@RequestBody Customer customer){


        return customerService.changeLoc(customer);

    }

    @ApiOperation("获取电话号码")
    @GetMapping("/getTele")
    public String getTele(@RequestParam String name){

        return customerService.getTele(name);

    }




}
