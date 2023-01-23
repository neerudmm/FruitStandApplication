package com.fruit.pojo;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class FruitStands {
    private final int id;
    private final Map<FruitEnum, Integer> fruitWithPrice;

    public boolean preferredFruitExists(Customer customer) {
        return fruitWithPrice.keySet().containsAll(customer.getPreferences());
    }
}
