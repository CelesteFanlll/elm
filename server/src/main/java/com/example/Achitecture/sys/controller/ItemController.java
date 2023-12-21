package com.example.Achitecture.sys.controller;

import com.example.Achitecture.common.BaseAbstractClass.BaseController;
import com.example.Achitecture.sys.entity.Item;
import com.example.Achitecture.sys.mapper.ItemMapper;
import com.example.Achitecture.sys.service.IItemService;
import com.example.Achitecture.sys.service.impl.ItemServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-12-02
 */

@Api(tags = "商品的控制器")
@RestController
@RequestMapping("/sys/item")

public class ItemController extends BaseController<ItemServiceImpl, ItemMapper, Item, Long> {

    public ItemController(ItemServiceImpl ls) {
        super(ls);
    }

    @Autowired
    IItemService itemService;


    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @ApiOperation("通过商店的ID查询他的已上架的商品")
    @GetMapping("/getItem")
    public List<Item> getItem(@RequestParam int id) {

        return itemService.getItem(id);


    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @ApiOperation("查询所有商店所属的物品")
    @GetMapping("/getItemByShopId")
    public List<Item> getItemByShopId(@RequestParam int id) {


        return itemService.getItem(id);
    }


}
