package com.dkraus.application.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dkraus.application.database.entities.Car;
import com.dkraus.application.database.repositories.CarRepository;

@Service
public class CarBusinessService {

	private final CarRepository repository;

	public CarBusinessService(CarRepository repository) {
		this.repository = repository;
	}

	public List<Car> findAll() {
		return repository.findAll();
	}

	public Car findById(long id) {
		return repository.getReferenceById(id);
	}
}
