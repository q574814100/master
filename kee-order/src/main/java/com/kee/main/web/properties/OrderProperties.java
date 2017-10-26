package com.kee.main.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "remote") //只会引入remote 开头
public class OrderProperties {

    private ItemProperties item= new ItemProperties();

    public ItemProperties getItem() {
        return item;
    }

    public void setItem(ItemProperties item) {
        this.item = item;
    }
}
