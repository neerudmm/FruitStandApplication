package com.fruit.pojo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class Customer {
    private final String name;
    @NonNull
    private final List<FruitEnum> preferences;
    @Setter
    private List<FruitEnum> fruitBought;
}
