package com.fruit.dao;

import java.util.List;

import com.fruit.pojo.FruitStands;

public interface FruitStandDAO {

	public List<FruitStands> getFruitStands();
	public List<FruitStands> getFruitStandsWithPears();


}
