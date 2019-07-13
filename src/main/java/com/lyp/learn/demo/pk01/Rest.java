package com.lyp.learn.demo.pk01;

import com.alibaba.fastjson.JSONObject;
import com.lyp.learn.demo.pk01.beans.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequestMapping("/v1/orders")
@Controller
public class Rest {

    private static final String  SUCCESS = "success";

    static Map<Integer,Order> orderMaps = new ConcurrentHashMap<>();
    /**
     * 新增 order
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/",method=RequestMethod.POST)
    public String saveOrder(Order order){
        System.out.println(order);
        orderMaps.put(order.getId(),order);
        return JSONObject.toJSONString(order);
    }

    /**
     * 删除订单
     * 测试用，返回删除对象，实际使用，不用返回删除对象
     * @param id
     * @return
     */
    @ResponseBody
//    @DeleteMapping(value = "/{id}")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteOrder(@PathVariable("id") Integer id){
        System.out.println(id);
        Order order = orderMaps.get(id);
        orderMaps.remove(id);
        return JSONObject.toJSONString(order);
    }

    /**
     * 修改订单(全部属性的修改)
     * @param id
     * @param order
     * @return
     */
    @ResponseBody
    @PutMapping(value = "/{id}")
    public String editOrder(@PathVariable("id") Integer id,Order order){
        System.out.println(id);
        Order originOrder = orderMaps.get(id);
        if(order.getName() != null && order.getName() != ""){
            originOrder.setName(order.getName());
        }
        if(order.getWeight() > 0){
            originOrder.setWeight(order.getWeight());
        }

        orderMaps.put(id,originOrder);
        return JSONObject.toJSONString(originOrder);
    }

    /**
     * 修改订单(部分属性的修改)
     * @return
     */
    @ResponseBody
    @PatchMapping(value = "/{id}")
    public String editOrderPart(@PathVariable("id") Integer id,String name){
        Order originOrder = orderMaps.get(id);
        originOrder.setName(name);
        orderMaps.put(id,originOrder);
        return JSONObject.toJSONString(originOrder);
    }

    /**
     * 查询所有订单
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String queryOrders(){
        return JSONObject.toJSONString(orderMaps);
    }

    /**
     * 查询单个订单
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String queryOrder(@PathVariable("id") Integer id){
        System.out.println(id);
        return JSONObject.toJSONString(orderMaps.get(id));
    }
}
