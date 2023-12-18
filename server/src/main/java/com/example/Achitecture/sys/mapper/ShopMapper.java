package com.example.Achitecture.sys.mapper;

import com.example.Achitecture.sys.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wzq
 * @since 2023-12-02
 */
@Mapper
public interface ShopMapper extends BaseMapper<Shop> {

    List<Shop> search(String keyword);


}
