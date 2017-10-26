package com.kee.main.web.service;

import com.kee.main.web.model.Item;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ItemService {

    private static final Map<Long, Item> MAP = new HashMap<>();


    static { // 准备一些静态数据
        MAP.put(1L, new Item(1L, "商品标题1", "http://图片1", "商品描述1", 1000L));
        MAP.put(2L, new Item(1L, "商品标题2", "http://图片2", "商品描述2", 2000L));
        MAP.put(3L, new Item(1L, "商品标题3", "http://图片3", "商品描述3", 3000L));
        MAP.put(4L, new Item(1L, "商品标题4", "http://图片4", "商品描述4", 4000L));
        MAP.put(5L, new Item(1L, "商品标题5", "http://图片5", "商品描述5", 5000L));
        MAP.put(6L, new Item(1L, "商品标题6", "http://图片6", "商品描述6", 6000L));
        MAP.put(7L, new Item(1L, "商品标题7", "http://图片7", "商品描述7", 7000L));
        MAP.put(8L, new Item(1L, "商品标题8", "http://图片8", "商品描述8", 8000L));
    }

    /**
     * 模拟实现商品查询
     *
     * @param id
     * @return
     */
    public Item queryItemById(Long id) {
        return MAP.get(id);
    }


}
