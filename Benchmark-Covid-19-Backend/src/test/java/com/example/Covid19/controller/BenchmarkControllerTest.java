package com.example.Covid19.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Covid19.model.Benchmark;
import com.example.Covid19.model.ResultCityState;
import com.example.Covid19.model.ResultCountry;
import com.example.Covid19.service.BenchmarkService;
import com.example.Covid19.service.ResultCityStateService;
import com.example.Covid19.service.ResultCountryService;

public class BenchmarkControllerTest {

    @Mock
    private BenchmarkService benchmarkService;

    @Mock
    private ResultCityStateService resultCityStateService;

    @Mock
    private ResultCountryService resultCountryService;

    @InjectMocks
    private BenchmarkController benchmarkController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBenchmark() {
        List<Benchmark> benchmarks = new ArrayList<>();
        when(benchmarkService.getAllBenchmark()).thenReturn(benchmarks);


        ResponseEntity<List<Benchmark>> response = benchmarkController.getAllBenchmark();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetBenchmarkById() {
        Benchmark benchmark = new Benchmark();
        when(benchmarkService.getBenchmarkById(1L)).thenReturn(benchmark);


        ResponseEntity<Benchmark> response = benchmarkController.getBenchmarkById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(benchmark, response.getBody());
    }
    

    @Test
    public void testCreateBenchmark() {
        Benchmark benchmark = new Benchmark();
        when(benchmarkService.createBenchmark(benchmark)).thenReturn(benchmark);


        ResponseEntity<Benchmark> response = benchmarkController.createBenchmark(benchmark);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(benchmark, response.getBody());
    }


    @Test
    public void testUpdateBenchmark() {
        Benchmark updatedBenchmark = new Benchmark();
        when(benchmarkService.updateBenchmarkById(1L, updatedBenchmark)).thenReturn(updatedBenchmark);

        ResponseEntity<Benchmark> response = benchmarkController.updateBenchmark(1L, updatedBenchmark);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBenchmark, response.getBody());
    }


    @Test
    public void testDeleteBenchmark() {
        ResponseEntity<Void> response = benchmarkController.deleteBenchmark(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


    @Test
    public void testGetResultCityStateByBenchmarkId() {
        Benchmark benchmark = new Benchmark();
        ResultCityState resultCityState = new ResultCityState();
        benchmark.setResult(resultCityState); 

        when(benchmarkService.getBenchmarkById(1L)).thenReturn(benchmark);
        when(resultCityStateService.getResultCityStateById(resultCityState.getId())).thenReturn(resultCityState);


        ResponseEntity<ResultCityState> response = benchmarkController.getResultCityStateByBenchmarkId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resultCityState, response.getBody());
    }


    @Test
    public void testGetResultCountryByBenchmarkId() {
        List<ResultCountry> resultCountries = new ArrayList<>();
        when(resultCountryService.getAllResultCountryByBenchmarkId(1L)).thenReturn(resultCountries);


        ResponseEntity<List<ResultCountry>> response = benchmarkController.getResultCountryByBenchmarkId(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    
}
