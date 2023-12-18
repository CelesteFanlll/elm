package com.example.Achitecture.common.Factory;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.Achitecture.common.BaseAbstractClass.AbstractFactory;
import com.example.Achitecture.sys.entity.Business;
import com.example.Achitecture.sys.mapper.BusinessMapper;
import com.example.Achitecture.sys.service.IBusinessService;
import com.example.Achitecture.sys.service.impl.BusinessServiceImpl;

public class BusinessFactory implements AbstractFactory {

    @Override
    public Business createEntity() {
        return new Business();
    }



    @Override
    public IBusinessService createService() {
        return new BusinessServiceImpl();
    }
}
