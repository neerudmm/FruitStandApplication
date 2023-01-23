package com.fruit.service.impl;

import java.util.List;

import com.fruit.dao.FruitStandDAO;
import com.fruit.dao.impl.FruitStandDAOImpl;
import com.fruit.pojo.FruitStands;
import com.fruit.service.FruitStandService;

public class FruitStandServiceImpl implements FruitStandService {

	public List<FruitStands> getFruitStands() {
		FruitStandDAO fdo = new FruitStandDAOImpl();
		return fdo.getFruitStands();
	}
	
	public List<FruitStands> getFruitStandsWithPears() {
		FruitStandDAO fdo = new FruitStandDAOImpl();
		return fdo.getFruitStandsWithPears();
	}

}
