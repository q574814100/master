package com.kee.main.web.controller;


import com.kee.main.web.model.Item;
import com.kee.main.web.properties.JdbcConfigBean;
import com.kee.main.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private JdbcConfigBean jdbcConfigBean;

    /**
     * 对外提供接口服务，查询商品信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "item/{id}")
    public Item queryItemById(@PathVariable("id") Long id) {
        return this.itemService.queryItemById(id);
    }


    /**
     *  测试注入的数据
     */
    @GetMapping(value = "/test")
    public void queryJdbcConfig() {

        System.out.println(jdbcConfigBean.toString());

    }


}
