package com.example.Covid19.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Covid19.model.ResultCityState;
import com.example.Covid19.repository.ResultCityStateRepository;

/**
 * Serviços para ResultCityState
 * 
 * Utiliza o repositório resultCityStateRepository para acesso ao banco de dados
 */
@Service
public class ResultCityStateService {

    private final ResultCityStateRepository resultCityStateRepository;

    @Autowired
    public ResultCityStateService( ResultCityStateRepository resultRepository) {
    	this.resultCityStateRepository = resultRepository;
 
    }
    
    // Método para obter um resultado de benchmark entre cidades ou estados pelo ID
    public ResultCityState getResultCityStateById(Long id) {
    	if(id == null) throw new NoSuchElementException();
    	
        return resultCityStateRepository.findById(id).orElseThrow();
    }

}
