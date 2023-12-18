package com.example.Achitecture.sys.controller;

import com.example.Achitecture.common.BaseAbstractClass.BaseController;
import com.example.Achitecture.common.DTO.Result;
import com.example.Achitecture.common.DTO.TClassDTO;
import com.example.Achitecture.sys.entity.ListItem;
import com.example.Achitecture.sys.mapper.ListItemMapper;
import com.example.Achitecture.sys.service.IListItemService;
import com.example.Achitecture.sys.service.impl.ListItemServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzq
 * @since 2023-12-02
 */
@Api(tags = "订单详细物品的控制器")
@RestController
@RequestMapping("/sys/listItem")
public class ListItemController extends BaseController<ListItemServiceImpl, ListItemMapper, ListItem,Long> {

    public ListItemController(ListItemServiceImpl ls) {
        super(ls);
    }

    @Autowired
    IListItemService listItemService;

    @ApiOperation("通过订单号查询详情")
    @GetMapping("/getOrderDetail")
    public List<TClassDTO> getOrderDetail(@RequestParam String keyword){

        return listItemService.getOrderDetail(keyword);

    }

    @ApiOperation("根据前端传来的数组创建Listitem")
    @PostMapping("/createListitem")
    public Result<?> createListitem(@RequestBody List<ListItem> list){
        listItemService.createListitem(list);

        return Result.success("成功");

    }

}
