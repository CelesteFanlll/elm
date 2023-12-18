package com.example.Achitecture.sys.service;

import com.example.Achitecture.common.DTO.Result;
import com.example.Achitecture.common.DTO.TClassDTO;
import com.example.Achitecture.sys.entity.ListItem;
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
public interface IListItemService extends IService<ListItem> {

    List<TClassDTO> getOrderDetail(String keyword);

    void createListitem(List<ListItem> list);
}
