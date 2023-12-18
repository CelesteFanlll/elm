package com.example.Achitecture.sys.service;

import com.example.Achitecture.sys.entity.Item;
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
public interface IItemService extends IService<Item> {

    List<Item> getItem(int id);


}
