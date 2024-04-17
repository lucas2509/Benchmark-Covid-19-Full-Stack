package com.example.Covid19.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Covid19.model.ResultCountry;
import com.example.Covid19.repository.ResultCountryRepository;

public class ResultCountryServiceTest {

    private ResultCountryService resultCountryService;
    private ResultCountryRepository resultCountryRepository;

    @BeforeEach
    public void setUp() {
        resultCountryRepository = mock(ResultCountryRepository.class);
        resultCountryService = new ResultCountryService(resultCountryRepository);
    }

    @Test
    public void testGetAllResultCountryByBenchmarkId() {
    	Long id = (long) 1;
        List<ResultCountry> resultCountries = new ArrayList<>();
        
        LocalDate date = LocalDate.of(2021, 3, 6); 
        resultCountries.add(new ResultCountry(1,"cases",date,100,null));
        resultCountries.add(new ResultCountry(1,"deaths",date,5,null));
        
        when(resultCountryRepository.findAllByBenchmarkId(id)).thenReturn(resultCountries);

        List<ResultCountry> retrievedResultCountries = resultCountryService.getAllResultCountryByBenchmarkId(id);
 
        assertEquals(resultCountries.size(), retrievedResultCountries.size());
        
    }
}

