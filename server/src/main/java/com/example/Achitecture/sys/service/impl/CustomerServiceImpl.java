package com.example.Achitecture.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.Achitecture.common.Factory.CustomerFactory;
import com.example.Achitecture.common.BaseAbstractClass.AbstractFactory;
import com.example.Achitecture.common.util.JwtUtil;
import com.example.Achitecture.sys.entity.Customer;
import com.example.Achitecture.sys.mapper.CustomerMapper;
import com.example.Achitecture.sys.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzq
 * @since 2023-12-02
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Autowired
    CustomerMapper customerMapper;

    AbstractFactory<Customer> customerFactory = new CustomerFactory();
    Customer loginCustomer =  customerFactory.createEntity();




    @Override
    public Map<String, Object> login(Customer customer) {
        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Customer::getCustomerName, customer.getCustomerName());
        queryWrapper.eq(Customer::getPassword, customer.getPassword());
        loginCustomer = this.baseMapper.selectOne(queryWrapper);
        if (loginCustomer != null) {
            JwtUtil.initializeSignatureKey("Customer");
            loginCustomer.setToken(JwtUtil.createToken());

            Map<String, Object> data = new HashMap<>();
            data.put("token", loginCustomer.getToken());
            return data;
        }
        return null;
    }

    @Override
    public boolean signup(Customer customer) {
        customerMapper.insert(customer);
        return true;
    }
    @Override
    public Integer changeLoc(Customer customer) {
        LambdaUpdateWrapper<Customer> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Customer::getCustomerName,customer.getCustomerName())
                .set(Customer::getLoc,customer.getLoc());
        Integer rows = customerMapper.update(null, updateWrapper);
        return rows;
    }
    @Override
    public String searchLoc(String name) {
        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Customer::getCustomerName,name);
        return customerMapper.selectOne(queryWrapper).getLoc();
    }

    @Override
    public String getTele(String name) {
        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Customer::getCustomerName,name);
        return customerMapper.selectOne(queryWrapper).getPhone();
    }
}
