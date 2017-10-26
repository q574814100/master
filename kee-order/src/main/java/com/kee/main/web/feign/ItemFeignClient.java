package com.kee.main.web.feign;

import com.kee.main.web.model.Item;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//指定fegin 客户端的服务id
@FeignClient(value = "limeo-item")
public interface ItemFeignClient {

    /**
     * fegin 可以使用springMVC注解 取代feign的注解
     * @param id
     * @return
     */
    @GetMapping(value = "/item/{id}")
    public Item queryItemById(@PathVariable("id") Long id);
}
