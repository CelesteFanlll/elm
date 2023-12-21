package com.example.Achitecture.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.Achitecture.common.Factory.ItemFactory;
import com.example.Achitecture.common.BaseAbstractClass.AbstractFactory;
import com.example.Achitecture.sys.entity.Item;
import com.example.Achitecture.sys.mapper.ItemMapper;
import com.example.Achitecture.sys.service.IItemService;
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
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    @Autowired
    ItemMapper itemMapper;

    AbstractFactory<Item> itemFactory = new ItemFactory();
    Item item = itemFactory.createEntity();

    @Override
    public List<Item> getItem(int id) {
        LambdaQueryWrapper<Item> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Item::getShopId,id);
        return itemMapper.selectList(queryWrapper);
    }


}
