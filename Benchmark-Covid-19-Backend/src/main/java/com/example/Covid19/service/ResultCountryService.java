package com.example.Covid19.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.Covid19.model.ResultCountry;
import com.example.Covid19.repository.ResultCountryRepository;

/**
 * Serviços para ResultCountry
 * 
 * Utiliza o repositório resultCountryRepository para acesso ao no banco de dados
 */
@Service
public class ResultCountryService {

	private final ResultCountryRepository resultCountryRepository;
	
	@Autowired
    public ResultCountryService( ResultCountryRepository resultCountryRepository) {
    	this.resultCountryRepository = resultCountryRepository;
 
    }
	
	//Obtém todos os resultados para um determinado ID de benchmark
	public List<ResultCountry> getAllResultCountryByBenchmarkId(Long benchmarkId) {
    	return resultCountryRepository.findAllByBenchmarkId(benchmarkId);
    }

}
