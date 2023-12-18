package com.example.Achitecture.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.Achitecture.sys.entity.Business;
import com.example.Achitecture.sys.entity.Shop;
import com.example.Achitecture.sys.mapper.BusinessMapper;
import com.example.Achitecture.sys.mapper.ShopMapper;
import com.example.Achitecture.sys.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {


    @Autowired
    ShopMapper shopMapper;
    @Autowired
    BusinessMapper businessMapper;
    @Override
    public List<Shop> search(String keyword) {
        return shopMapper.search(keyword);
    }

    @Override
    public List<Shop> getShop(Business business) {
        LambdaQueryWrapper<Business> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Business::getBusinessName,business.getBusinessName());
        LambdaQueryWrapper<Shop> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Shop::getBusinessId,businessMapper.selectOne(queryWrapper1).getBusinessId());
        return shopMapper.selectList(queryWrapper);
    }

    @Override
    public void addShop(Shop shop) {

        shopMapper.insert(shop);

    }

    @Override
    public List<Shop> getByTag(String tag) {

        LambdaQueryWrapper<Shop> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Shop::getTag,tag);

        return shopMapper.selectList(queryWrapper);
    }
}
