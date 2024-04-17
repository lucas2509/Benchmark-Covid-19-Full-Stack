package com.example.Covid19.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Covid19.model.ResultCountry;

@Repository
public interface ResultCountryRepository extends JpaRepository<ResultCountry, Long> {
	
	//Método adicionado para excluir todos os ResultCountry a partir da ID do Benchmark
	void deleteByBenchmarkId(Long benchmarkId);
	
	//Método adicionado para encontrar tods os ResultCountry a partir da ID do Benchmark
	List<ResultCountry> findAllByBenchmarkId(Long benchmarkId);
}
