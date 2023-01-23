package com.fruit.dao.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fruit.dao.FruitStandDAO;
import com.fruit.pojo.FruitEnum;
import com.fruit.pojo.FruitStands;

public class FruitStandDAOImpl implements FruitStandDAO {

	public List<FruitStands> getFruitStands() {
		return Arrays.asList(
				FruitStands.builder().id(1)
						.fruitWithPrice(
								Map.ofEntries(Map.entry(FruitEnum.CHERRIES, 50), Map.entry(FruitEnum.PEACHES, 30)))
						.build(),
				FruitStands.builder().id(2)
						.fruitWithPrice(
								Map.ofEntries(Map.entry(FruitEnum.CHERRIES, 80), Map.entry(FruitEnum.PEACHES, 20)))
						.build(),
				FruitStands.builder().id(3)
						.fruitWithPrice(
								Map.ofEntries(Map.entry(FruitEnum.CHERRIES, 60), Map.entry(FruitEnum.PEACHES, 2)))
						.build(),
				FruitStands.builder().id(4)
						.fruitWithPrice(
								Map.ofEntries(Map.entry(FruitEnum.CHERRIES, 20), Map.entry(FruitEnum.PEACHES, 20)))
						.build());
	}

	public List<FruitStands> getFruitStandsWithPears() {
		return Arrays.asList(
				FruitStands.builder().id(1)
						.fruitWithPrice(Map.ofEntries(Map.entry(FruitEnum.PEARS, 50), Map.entry(FruitEnum.PEACHES, 20),
								Map.entry(FruitEnum.CHERRIES, 30)))
						.build(),

				FruitStands.builder().id(2)
						.fruitWithPrice(Map.ofEntries(Map.entry(FruitEnum.PEARS, 80), Map.entry(FruitEnum.PEACHES, 20),
								Map.entry(FruitEnum.CHERRIES, 20)))
						.build(),
				FruitStands.builder().id(3)
						.fruitWithPrice(Map.ofEntries(Map.entry(FruitEnum.PEARS, 50), Map.entry(FruitEnum.PEACHES, 20),
								Map.entry(FruitEnum.CHERRIES, 70)))
						.build(),
						
				FruitStands.builder().id(4).fruitWithPrice(Map.ofEntries(Map.entry(FruitEnum.PEARS, 20),
						Map.entry(FruitEnum.PEACHES, 40), Map.entry(FruitEnum.CHERRIES, 20))).build(),
				
				FruitStands.builder().id(5).fruitWithPrice(Map.ofEntries(Map.entry(FruitEnum.PEARS, 10),
						Map.entry(FruitEnum.PEACHES, 80), Map.entry(FruitEnum.CHERRIES, 10))).build(),
				
				FruitStands.builder().id(6).fruitWithPrice(Map.ofEntries(Map.entry(FruitEnum.PEARS, 20),
						Map.entry(FruitEnum.PEACHES, 30), Map.entry(FruitEnum.CHERRIES, 20))).build(),
				
				FruitStands.builder().id(7).fruitWithPrice(Map.ofEntries(Map.entry(FruitEnum.PEARS, 30),
						Map.entry(FruitEnum.PEACHES, 40), Map.entry(FruitEnum.CHERRIES, 60))).build()
				
				);
	}

}
