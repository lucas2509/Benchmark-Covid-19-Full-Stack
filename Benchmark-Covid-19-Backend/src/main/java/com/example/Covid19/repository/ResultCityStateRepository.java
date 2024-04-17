package com.example.Covid19.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Covid19.model.ResultCityState;

@Repository
public interface ResultCityStateRepository extends JpaRepository<ResultCityState, Long> {
}
