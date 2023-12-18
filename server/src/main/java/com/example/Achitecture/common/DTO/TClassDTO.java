package com.example.Achitecture.common.DTO;


import com.example.Achitecture.sys.entity.COrder;
import com.example.Achitecture.sys.entity.Item;
import com.example.Achitecture.sys.entity.ListItem;
import com.example.Achitecture.sys.entity.Shop;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class TClassDTO {


    COrder cOrder;
    Shop shop;

    ListItem listItem;
    Item item;






}
