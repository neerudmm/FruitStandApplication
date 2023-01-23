package com.fruit.pojo;

import lombok.ToString;

@ToString
public enum FruitEnum {
    CHERRIES("Cherries"),
    PEACHES("Peaches"),
    PEARS("Pears");

    public final String name;

    FruitEnum(String name) {
        this.name = name;
    }

}
