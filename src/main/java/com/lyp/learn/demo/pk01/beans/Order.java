package com.lyp.learn.demo.pk01.beans;


public class Order {
    private Integer id;
    private String name;
    private Integer weight;

    public Order() {
    }

    public Order(Integer id, String name,Integer weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
