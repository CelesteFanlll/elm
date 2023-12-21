package com.example.Achitecture.common.Factory;

import com.example.Achitecture.common.BaseAbstractClass.AbstractFactory;
import com.example.Achitecture.sys.entity.Business;

public class BusinessFactory implements AbstractFactory {
    @Override
    public Business createEntity() {
        return new Business();
    }
}
