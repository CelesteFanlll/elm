package com.example.Achitecture.common.BaseAbstractClass;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;


@Api(tags ="基础Controller")
@CrossOrigin
public abstract class BaseController<S extends ServiceImpl<D,T>, D extends BaseMapper<T>,T extends Serializable, ID extends Serializable>{

    protected S service;
    protected S getService(){
        return service;
    }
    public BaseController(S ls){
        this.service = ls;
    }

    @GetMapping("/get")
    @ApiOperation("通过ID获取实体")
    public T GET(@RequestParam ID id){
        return service.getById(id);
    }

    @ApiOperation("获取所有实体")
    @GetMapping("/getall")
    public List<T> getAll(){
        return service.list(null);
    }

    @ApiOperation("修改实体")
    @PostMapping("/put")
    public boolean PUT(@RequestBody T entity){
        return service.updateById(entity);
    }

    @ApiOperation("创建实体")
    @PutMapping("/post")
    public boolean POST(@RequestBody T  entity){
        return service.save(entity);
    }

    @ApiOperation("通过ID删除实体")
    @DeleteMapping("/delete")
    public void DELETE(@RequestParam ID id){
        service.removeById(id);
    }

}
