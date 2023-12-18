package com.example.Achitecture.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.Achitecture.common.util.JwtUtil;
import com.example.Achitecture.sys.entity.Business;
import com.example.Achitecture.sys.entity.Customer;
import com.example.Achitecture.sys.mapper.BusinessMapper;
import com.example.Achitecture.sys.service.IBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzq
 * @since 2023-12-02
 */
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements IBusinessService {

    @Override
    public Map<String, Object> login(Business business) {
        LambdaQueryWrapper<Business> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Business::getBusinessName,business.getBusinessName());
        queryWrapper.eq(Business::getBusinessPassword,business.getBusinessPassword());
        Business loginBusiness = this.baseMapper.selectOne(queryWrapper);
        if (loginBusiness!= null){
            JwtUtil.initializeSignatureKey("Business");
            JwtUtil.initializeName(loginBusiness.getBusinessName());
            loginBusiness.setToken(JwtUtil.createToken());
            Map<String,Object> data = new HashMap<>();
            data.put("token",loginBusiness.getToken());
            data.put("id",loginBusiness.getBusinessId());
            return data;
        }
        return null;
    }
}
