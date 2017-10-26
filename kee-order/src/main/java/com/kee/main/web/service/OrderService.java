package com.kee.main.web.service;

import com.kee.main.web.feign.ItemFeignClient;
import com.kee.main.web.model.Item;
import com.kee.main.web.model.Order;
import com.kee.main.web.model.OrderDetail;
import com.kee.main.web.properties.OrderProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OrderService {
    private static final Map<String, Order> MAP = new HashMap<String, Order>();

//    @Value("${item.url}")
//    private String itemUrl;
    @Autowired
    private OrderProperties orderProperties;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ItemFeignClient itemFeignClient;

    static {
        // 构造测试数据
        Order order = new Order();
        order.setOrderId("59193738268961441");
        order.setCreateDate(new Date());
        order.setUpdateDate(order.getCreateDate());
        order.setUserId(1L);
        List<OrderDetail> orderDetails = new ArrayList<>();

        Item item = new Item();// 此处并没有商品的数据，需要调用商品微服务获取
        item.setId(1L);
        orderDetails.add(new OrderDetail(order.getOrderId(), item));

        item = new Item(); // 构造第二个商品数据
        item.setId(2L);
        orderDetails.add(new OrderDetail(order.getOrderId(), item));

        order.setOrderDetails(orderDetails);

        MAP.put(order.getOrderId(), order);
    }


    /**
     * 根据订单id查询订单数据
     *
     * @param orderId
     * @return
     */
    public Order queryOrderById(String orderId) {
        Order order = MAP.get(orderId);
        if (null == order) {
            return null;
        }
        List<OrderDetail> orderDetails = order.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            // 通过商品微服务查询商品数据
            Item item = this.queryItemById(orderDetail.getItem()
                    .getId());
            if (null == item) {
                continue;
            }
            orderDetail.setItem(item);
        }

        return order;
    }


//    public Item queryItemById(Long id) {
//        return this.restTemplate.getForObject( orderProperties.getItem().getUrl() + id, Item.class);
//    }

    /** eureka
     *
     * @param id
     * @return
     */
//    public Item queryItemById(Long id) {
//        String serviceId = "limeo-item";
//        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
//        if(instances.isEmpty()){
//            return null;
//        }
//        // 为了演示，在这里只获取一个实例
//        ServiceInstance serviceInstance = instances.get(0);
//        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
//        return this.restTemplate.getForObject("http://" + url + "/item/" + id, Item.class);
//    }

    /**
     *  使用ribbon 面向服务 根据服务id 会对应寻找服务对应的url
     * @param id
     * @return
     */
//    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") // 服务出错 hystrix 会去执行指定的方法
//    public Item queryItemById(Long id) {
//        String serviceId = "limeo-item";
//        return this.restTemplate.getForObject("http://" + serviceId + "/item/" + id, Item.class);
//    }


    public Item queryItemByIdFallbackMethod(Long id){
        Item item = new Item();
        item.setId(id);
        item.setTitle("查询商品出错");
        return item;
    }

    /**
     * feign 实现声明式请求
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") // 服务出错 hystrix 会去执行指定的方法
    public Item queryItemById(Long id) {
        return itemFeignClient.queryItemById(id);
    }

}
