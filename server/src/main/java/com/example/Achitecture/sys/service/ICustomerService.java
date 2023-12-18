package com.example.Achitecture.sys.service;

import com.example.Achitecture.sys.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzq
 * @since 2023-12-02
 */
public interface ICustomerService extends IService<Customer> {

    Map<String, Object> login(Customer customer);

    boolean signup(Customer customer);

    Integer changeLoc(Customer customer);

    String searchLoc(String name);

    String getTele(String name);
}
