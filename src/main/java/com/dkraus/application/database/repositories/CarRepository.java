package com.dkraus.application.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkraus.application.database.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
