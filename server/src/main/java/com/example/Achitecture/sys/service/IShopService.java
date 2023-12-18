package com.example.Achitecture.sys.service;

import com.example.Achitecture.sys.entity.Business;
import com.example.Achitecture.sys.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzq
 * @since 2023-12-02
 */
public interface IShopService extends IService<Shop> {

    List<Shop> search(String keyword);

    List<Shop> getShop(Business business);

    void addShop(Shop shop);

    List<Shop> getByTag(String tag);
}
