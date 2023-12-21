package com.example.Achitecture.sys.controller;

import com.example.Achitecture.common.BaseAbstractClass.BaseController;
import com.example.Achitecture.common.DTO.Result;
import com.example.Achitecture.sys.entity.Business;

import com.example.Achitecture.sys.mapper.BusinessMapper;
import com.example.Achitecture.sys.service.IBusinessService;
import com.example.Achitecture.sys.service.impl.BusinessServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-12-02
 */

@Api(tags = "商家的控制器")
@RestController
@RequestMapping("/sys/business")
public class BusinessController extends BaseController<BusinessServiceImpl, BusinessMapper, Business, Long> {

//    基础方法的实现
    public BusinessController(BusinessServiceImpl ls) {
        super(ls);
    }

    @Autowired
    IBusinessService businessService;

    @Autowired
    private UserDetailsService userDetailsService;



    @ApiOperation("商家登录")
    @PostMapping("/login")
    public Result<?> login(@RequestBody Business business){




            Map<String, Object> data = businessService.login(business);

            if (data != null) {
                return Result.success(data);
            }
            // 登录失败处理
            return Result.fail(20001, "用户或密码错误");


    }



}
