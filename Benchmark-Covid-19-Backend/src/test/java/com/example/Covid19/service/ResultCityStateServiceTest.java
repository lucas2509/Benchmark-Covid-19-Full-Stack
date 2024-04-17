package com.example.Covid19.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Covid19.model.ResultCityState;
import com.example.Covid19.repository.ResultCityStateRepository;

public class ResultCityStateServiceTest {

    private ResultCityStateService resultCityStateService;
    private ResultCityStateRepository resultCityStateRepository;

    @BeforeEach
    public void setUp() {
        resultCityStateRepository = mock(ResultCityStateRepository.class);
        resultCityStateService = new ResultCityStateService(resultCityStateRepository);
    }

    @Test
    public void testGetResultCityStateById_ExistingId() {
    	Long id = (long) 1;
    	
        ResultCityState expectedResult = new ResultCityState(20,30,2,3,200,300,20,30);
        when(resultCityStateRepository.findById(id)).thenReturn(Optional.of(expectedResult));

        ResultCityState result = resultCityStateService.getResultCityStateById(id);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetResultCityStateById_NonExistingId() {
    	Long id = (long) 1;
    	
        when(resultCityStateRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> resultCityStateService.getResultCityStateById(id));
    }
}
