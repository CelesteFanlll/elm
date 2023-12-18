package com.example.Achitecture.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.Achitecture.common.DTO.Result;
import com.example.Achitecture.common.DTO.TClassDTO;
import com.example.Achitecture.sys.entity.COrder;
import com.example.Achitecture.sys.entity.Item;
import com.example.Achitecture.sys.entity.ListItem;
import com.example.Achitecture.sys.entity.Shop;
import com.example.Achitecture.sys.mapper.ItemMapper;
import com.example.Achitecture.sys.mapper.ListItemMapper;
import com.example.Achitecture.sys.mapper.OrderMapper;
import com.example.Achitecture.sys.mapper.ShopMapper;
import com.example.Achitecture.sys.service.IListItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class ListItemServiceImpl extends ServiceImpl<ListItemMapper, ListItem> implements IListItemService {

    @Autowired
    ListItemMapper listItemMapper;
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ShopMapper shopMapper;


    @Override
    public List<TClassDTO> getOrderDetail(String keyword) {
        List<TClassDTO> result = new ArrayList<>();
        LambdaQueryWrapper<COrder> queryOrder = new LambdaQueryWrapper<>();
        queryOrder.eq(COrder::getOrderId,keyword);
        COrder cOrder = orderMapper.selectOne(queryOrder);
        LambdaQueryWrapper<Shop> queryShop = new LambdaQueryWrapper<>();
        queryShop.eq(Shop::getShopId,orderMapper.selectOne(queryOrder).getShopId());
        Shop shop = shopMapper.selectOne(queryShop);
        LambdaQueryWrapper<ListItem> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(ListItem::getOrderId, keyword);
        List<ListItem> List = listItemMapper.selectList(queryWrapper1);
        for (ListItem listItem : List) {
            TClassDTO dto = new TClassDTO();
            dto.setListItem(listItem);
            dto.setShop(shop);
            dto.setCOrder(cOrder);
            LambdaQueryWrapper<Item> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(Item::getItemId, listItem.getItemId());
            Item item = itemMapper.selectOne(queryWrapper2);
            dto.setItem(item);
            result.add(dto);
        }
        return  result;
    }

    @Override
    public void createListitem(List<ListItem> list) {

        for (ListItem item : list) {
            // 这里是模拟的代码，具体的insert方法你需要根据实际的mapper或数据库操作来写
            listItemMapper.insert(item);
        }

    }
}
