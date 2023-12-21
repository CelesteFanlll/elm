package com.example.Achitecture.sys.controller;

import com.example.Achitecture.common.BaseAbstractClass.BaseController;
import com.example.Achitecture.common.DTO.Result;
import com.example.Achitecture.sys.entity.Business;
import com.example.Achitecture.sys.entity.Shop;
import com.example.Achitecture.sys.mapper.ShopMapper;
import com.example.Achitecture.sys.service.IShopService;
import com.example.Achitecture.sys.service.impl.ShopServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-12-02
 */


@RestController
//@Secured({"ROLE_BUSINESS"})
@RequestMapping("/sys/shop")
@Api(tags = "商店的控制器")
public class ShopController extends BaseController<ShopServiceImpl, ShopMapper, Shop, Long> {

    public ShopController(ShopServiceImpl ls) {
        super(ls);
    }

    @Autowired
    IShopService shopService;


    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @ApiOperation("顾客搜索所有相关的店铺")
    @GetMapping("/search")
    public List<Shop> search(@RequestParam String keyword) {
        return shopService.search(keyword);
    }



    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @ApiOperation("根据商家的用户名查出所属的店铺")
    @PostMapping("/getShop")
    public List<Shop> getShop(@RequestBody Business business) {

        return shopService.getShop(business);

    }


    @PreAuthorize("hasRole('ROLE_BUSINESS')")
    @ApiOperation("新增商店")
    @PostMapping("/addShop")
    public Result<?> addShop(@RequestBody Shop shop) {

        shopService.addShop(shop);
        return Result.success("成功");

    }


    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @ApiOperation("根据tag查找商店")
    @GetMapping("/getByTag")
    public List<Shop> getByTag(@RequestParam String tag) {

        return shopService.getByTag(tag);

    }

    @Secured("ROLE_BUSINESS")
    @GetMapping("/test")
    public List<Shop> getALLL(){

        return shopService.list();
    }


}
