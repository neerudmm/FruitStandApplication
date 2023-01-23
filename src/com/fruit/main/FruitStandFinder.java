package com.fruit.main;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

import org.apache.log4j.Logger;

import com.fruit.pojo.Customer;
import com.fruit.pojo.FruitEnum;
import com.fruit.pojo.FruitStands;
import com.fruit.service.FruitStandService;
import com.fruit.service.impl.FruitStandServiceImpl;

public class FruitStandFinder {

	Logger log = Logger.getLogger(FruitStandFinder.class);

	public static void main(String[] args) {

		// Base scenario
		new FruitStandFinder().cheapestFruitStand();

		// Extension 1&2
		new FruitStandFinder().printStandWithFruitsAndPrice();

		// Extension 3 & 4
		new FruitStandFinder().printSuitableNoOfStandsTraversed();

	}

	/**
	 * Method Will Find Cheapest Fruit Stand in a list of Stands With Customer
	 * preferred Fruits
	 */
	public void cheapestFruitStand() {

		// Create customers with names and preferences
		Customer pelle = Customer.builder().name("Pelle").preferences(List.of(FruitEnum.CHERRIES)).build();
		Customer kajsa = Customer.builder().name("Kajsa").preferences(List.of(FruitEnum.PEACHES)).build();

		FruitStandService fruitStandService = new FruitStandServiceImpl();
		// Create stands
		List<FruitStands> fruitStandList = fruitStandService.getFruitStands();
		if(fruitStandList ==null || fruitStandList.isEmpty()) {
			log.debug("Empty Stands");
			return;
		}

		// Find stand with minimum price
		FruitStands cheapestFruitStandWithPrice = fruitStandList.stream()
				.filter(s -> s.preferredFruitExists(pelle) && s.preferredFruitExists(kajsa)).min((s1, s2) -> {
					int totalPriceS1 = s1.getFruitWithPrice().values().stream().mapToInt(Integer::intValue).sum();
					int totalPriceS2 = s2.getFruitWithPrice().values().stream().mapToInt(Integer::intValue).sum();
					if (totalPriceS1 == totalPriceS2)
						return s1.getId() - s2.getId();
					return totalPriceS1 - totalPriceS2;
				}).orElseThrow();

		log.debug("");
		log.debug("<--- Base scenario Starts--->");
		log.debug("Number of stands: " + fruitStandList.size());
		log.debug("List Of Fruit Stands :");
		fruitStandList.stream().forEach(s -> log.debug("  " + s));
		log.debug("Stand that has the lowest possible total price: ");
		log.debug(cheapestFruitStandWithPrice);

		log.debug("<--- Base scenario Ends--->");
	}

	/**
	 * Method Will Find Cheapest Fruit Stand With Price and Fruits that customer has
	 * bought
	 */
	public void printStandWithFruitsAndPrice() {
		// Build customers With preferences
		Customer pelle = Customer.builder().name("Pelle").preferences(List.of(FruitEnum.CHERRIES, FruitEnum.PEARS))
				.build();
		Customer kajsa = Customer.builder().name("Kajsa").preferences(List.of(FruitEnum.PEACHES, FruitEnum.PEARS))
				.build();
		List<Customer> customers = List.of(pelle, kajsa);
		// Create stands
		FruitStandService fruitStandService = new FruitStandServiceImpl();
		List<FruitStands> fruitStandList = fruitStandService.getFruitStandsWithPears();
		if(fruitStandList ==null || fruitStandList.isEmpty()) {
			log.debug("Empty Stands");
			return;
		}

		// Find stand with minimum price
		FruitStands bestWithPears = fruitStandList.stream().filter(s -> s.preferredFruitExists(pelle)
				&& s.preferredFruitExists(kajsa) && s.getFruitWithPrice().containsKey(FruitEnum.PEARS))
				.min((s1, s2) -> {
					int totalPriceS1 = s1.getFruitWithPrice().get(FruitEnum.PEARS)
							+ Math.min(s1.getFruitWithPrice().get(FruitEnum.CHERRIES),
									s1.getFruitWithPrice().get(FruitEnum.PEACHES));
					int totalPriceS2 = s2.getFruitWithPrice().get(FruitEnum.PEARS)
							+ Math.min(s2.getFruitWithPrice().get(FruitEnum.CHERRIES),
									s2.getFruitWithPrice().get(FruitEnum.PEACHES));
					if (totalPriceS1 == totalPriceS2)
						return s1.getId() - s2.getId();
					return totalPriceS1 - totalPriceS2;
				}).orElseThrow();

		FruitEnum otherFruit = bestWithPears.getFruitWithPrice().get(FruitEnum.CHERRIES) > bestWithPears
				.getFruitWithPrice().get(FruitEnum.PEACHES) ? FruitEnum.PEACHES : FruitEnum.CHERRIES;

		if (kajsa.getPreferences().contains(otherFruit)) {
			pelle.setFruitBought(List.of(FruitEnum.PEARS));
			kajsa.setFruitBought(List.of(otherFruit));
		} else {
			kajsa.setFruitBought(List.of(FruitEnum.PEARS));
			pelle.setFruitBought(List.of(otherFruit));
		}
		log.debug("");
		log.debug("<--- Extension 1&2 Starts--->");
		log.debug("Number of stands : " + fruitStandList.size());
		log.debug("List Of Fruit Stands :");
		fruitStandList.stream().forEach(s -> log.debug("  " + s));
		log.debug("Stand that has the lowest possible total price: " + bestWithPears);
		log.debug("Customer with Fruits : ");
		customers.stream().forEach(s -> log.debug("  " + s));
		log.debug("Price of Fruit Basket : " + (bestWithPears.getFruitWithPrice().get(FruitEnum.PEARS)
				+ bestWithPears.getFruitWithPrice().get(otherFruit)));
		log.debug("<--- Extension 1&2 Ends--->");
	}

	/**
	 * Method Will Find Cheapest Fruit Stand With Price and Fruits that customer has
	 * bought and Also no of useful stands for customer
	 */
	public void printSuitableNoOfStandsTraversed() {
		// Create customers with names and preferences
		Customer pelle = Customer.builder().name("Pelle").preferences(List.of(FruitEnum.CHERRIES, FruitEnum.PEARS))
				.build();
		Customer kajsa = Customer.builder().name("Kajsa").preferences(List.of(FruitEnum.PEACHES, FruitEnum.PEARS))
				.build();
		List<Customer> customers = List.of(pelle, kajsa);
		// Create stands
		FruitStandService fruitStandService = new FruitStandServiceImpl();
		List<FruitStands> fruitStandList = fruitStandService.getFruitStandsWithPears();
		if(fruitStandList ==null || fruitStandList.isEmpty()) {
			log.debug("Empty Stands");
			return;
		}

		List<FruitStands> usefulStands = new ArrayList<>();
		// Find stand with minimum price
		FruitStands bestWithPears = null;
		FruitEnum otherFruit = null;
		try {
			bestWithPears = fruitStandList.stream()
					.filter(s -> (s.preferredFruitExists(pelle) || s.preferredFruitExists(kajsa))
							&& s.getFruitWithPrice().containsKey(FruitEnum.PEARS))
					.peek(usefulStands::add).min((s1, s2) -> {
						int totalPriceS1 = s1.getFruitWithPrice().get(FruitEnum.PEARS)
								+ Math.min(s1.getFruitWithPrice().getOrDefault(FruitEnum.CHERRIES, Integer.MAX_VALUE),
										s1.getFruitWithPrice().getOrDefault(FruitEnum.PEACHES, Integer.MAX_VALUE));
						int totalPriceS2 = s2.getFruitWithPrice().get(FruitEnum.PEARS)
								+ Math.min(s2.getFruitWithPrice().getOrDefault(FruitEnum.CHERRIES, Integer.MAX_VALUE),
										s2.getFruitWithPrice().getOrDefault(FruitEnum.PEACHES, Integer.MAX_VALUE));
						if (totalPriceS1 == totalPriceS2)
							return s1.getId() - s2.getId();
						return totalPriceS1 - totalPriceS2;
					}).orElseThrow();

			otherFruit = bestWithPears.getFruitWithPrice().getOrDefault(FruitEnum.CHERRIES,
					Integer.MAX_VALUE) > bestWithPears.getFruitWithPrice().getOrDefault(FruitEnum.PEACHES,
							Integer.MAX_VALUE) ? FruitEnum.PEACHES : FruitEnum.CHERRIES;

			if (kajsa.getPreferences().contains(otherFruit)) {
				kajsa.setFruitBought(List.of(otherFruit));
				pelle.setFruitBought(List.of(FruitEnum.PEARS));
			} else {
				kajsa.setFruitBought(List.of(FruitEnum.PEARS));
				pelle.setFruitBought(List.of(otherFruit));
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		log.debug("");
		log.debug("<--- Extension 3 Starts--->");

		log.debug("Number of stands: " + fruitStandList.size());
		log.debug("List Of Fruit Stands :");
		fruitStandList.stream().forEach(s -> log.debug("  " + s));
		log.debug("Usefull Fruit Stands for Pelle & Kajsa : ");
		usefulStands.stream().forEach(s -> log.debug("  " + s));
		log.debug("Stand that has the lowest possible total price:  " + bestWithPears);
		log.debug("Customer with Fruits : ");
		customers.stream().forEach(s -> log.debug("  " + s));
		log.debug("Price of Fruit Basket : " + (bestWithPears.getFruitWithPrice().get(FruitEnum.PEARS)
				+ bestWithPears.getFruitWithPrice().get(otherFruit)));

		log.debug("<--- Extension 3 Ends--->");

		printForFriend(fruitStandList, bestWithPears);

	}

	/**
	 * 
	 * This method will print cheapest fruit stand with price and also Fruits bought
	 * by the friend.
	 * 
	 * @param fruitStandList
	 * @param bestStand
	 */
	public void printForFriend(List<FruitStands> fruitStandList, FruitStands bestStand) {

		List<FruitStands> usefulStandsForFriends = new ArrayList<>();
		// Find stand with minimum price
		FruitStands bestStandForFriend = null;

		Customer friend = Customer.builder().name("Jack")
				.preferences(List.of(FruitEnum.PEACHES, FruitEnum.CHERRIES, FruitEnum.PEARS)).build();
		List<Customer> customers = List.of(friend);

		try {

			// Ignore the best stand which is already used by Pelle Kajsa, choose the other
			// best stand.
			bestStandForFriend = fruitStandList.stream()
					.filter(s -> s.preferredFruitExists(friend) && s.getId() != bestStand.getId())
					.peek(usefulStandsForFriends::add).min((s1, s2) -> {
						int totalPriceS1 = s1.getFruitWithPrice().get(FruitEnum.PEARS)
								+ s1.getFruitWithPrice().get(FruitEnum.PEACHES)
								+ s1.getFruitWithPrice().get(FruitEnum.CHERRIES);
						int totalPriceS2 = s2.getFruitWithPrice().get(FruitEnum.PEARS)
								+ s2.getFruitWithPrice().get(FruitEnum.PEACHES)
								+ s2.getFruitWithPrice().get(FruitEnum.CHERRIES);
						if (totalPriceS1 == totalPriceS2)
							return s1.getId() - s2.getId();
						return totalPriceS1 - totalPriceS2;
					}).orElseThrow();

			friend.setFruitBought(new ArrayList<>(bestStandForFriend.getFruitWithPrice().keySet()));

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		log.debug("");
		log.debug("<--- Extension 4 Starts--->");

		log.debug("Number of stands: " + fruitStandList.size());
		log.debug("List Of Fruit Stands :");
		fruitStandList.stream().forEach(s -> log.debug("  " + s));
		log.debug("Preferred Fruits Stands for The Friend : ");
		usefulStandsForFriends.stream().forEach(s -> log.debug("  " + s));
		log.debug("Stand that has the lowest possible total price:  " + bestStandForFriend);
		log.debug("Fruits Purchased By Friend: ");
		log.debug(customers);

		log.debug("<--- Extension 4 Ends--->");
	}

}
