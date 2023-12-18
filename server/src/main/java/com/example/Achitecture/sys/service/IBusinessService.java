package com.example.Achitecture.sys.service;

import com.example.Achitecture.sys.entity.Business;
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
public interface IBusinessService extends IService<Business> {

    Map<String, Object> login(Business business);
}
