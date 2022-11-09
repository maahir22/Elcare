package com.armpipe.armpipe.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepressionController {
	List<String> depressionAnalyst = Arrays.asList("Not Depressed", "Mildly Depressed", "Moderately Depressed", "Severely Depressed", "Critically Depressed");
	@GetMapping("/depression")
	public String analysis() {
		return getRandomElement();
	}
	public String getRandomElement()
    {
        Random rand = new Random();
        return depressionAnalyst.get(rand.nextInt(depressionAnalyst.size()));
    }
}
