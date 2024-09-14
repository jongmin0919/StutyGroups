package com.packt.cardatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;

@RestController
@RequestMapping("/api")
public class CarController {
	@Autowired
	private CarRepository repository;

	@RequestMapping("/cars")
	public Iterable<Car> getCars() {
		return repository.findAll();
	}

	@RequestMapping("/car/{brand}")
	public Iterable<Car> getCarbyBrand(@PathVariable String brand){
		return repository.findByBrand(brand);
	}

	@PostMapping(value="/car/color")
	public Iterable<Car> getCarbyColor(@RequestBody String color){
		return repository.findByColor(color);
	}
}
