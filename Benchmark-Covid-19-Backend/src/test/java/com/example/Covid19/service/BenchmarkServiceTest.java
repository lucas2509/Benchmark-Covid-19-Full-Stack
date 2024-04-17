package com.example.Covid19.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpClientErrorException;

import com.example.Covid19.model.Benchmark;
import com.example.Covid19.model.ResultCountry;
import com.example.Covid19.repository.BenchmarkRepository;
import com.example.Covid19.repository.ResultCityStateRepository;
import com.example.Covid19.repository.ResultCountryRepository;


public class BenchmarkServiceTest {

    @Mock
    private BenchmarkRepository benchmarkRepository;

    @Mock
    private ResultCityStateRepository resultCityStateRepository;

    @Mock
    private ResultCountryRepository resultCountryRepository;

    @Mock
    private CovidAPIService covidAPIService;

    @InjectMocks
    private BenchmarkService benchmarkService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBenchmark() {
        List<Benchmark> benchmarks = new ArrayList<>();
        when(benchmarkRepository.findAll()).thenReturn(benchmarks);

        List<Benchmark> result = benchmarkService.getAllBenchmark();

        assertEquals(benchmarks, result);
    }

    @Test
    public void testGetBenchmarkById() {
        Long id = (long) 1;
        Benchmark benchmark = new Benchmark();
        when(benchmarkRepository.findById(id)).thenReturn(Optional.of(benchmark));

        Benchmark result = benchmarkService.getBenchmarkById(id);

        assertEquals(benchmark, result);
    }

    @Test
    public void testCreateBenchmarkForCountry() {
        Benchmark benchmark = new Benchmark(LocalDate.of(2021, 03, 06),LocalDate.of(2021, 12, 18),"country","US","Brazil",null);

        List<ResultCountry> resultCountries = new ArrayList<>();
        when(covidAPIService.getResultForCountry(benchmark)).thenReturn(resultCountries);
        when(benchmarkRepository.save(benchmark)).thenReturn(benchmark);

        Benchmark result = benchmarkService.createBenchmark(benchmark);
        assertEquals(benchmark, result);
        verify(resultCountryRepository, times(1)).saveAll(resultCountries);
    }
    
    @Test
    public void testCreateBenchmarkForCity() {
        Benchmark benchmark = new Benchmark(LocalDate.of(2020, 03, 18),LocalDate.of(2021, 11, 24),"city","São José dos Campos","São Paulo",null);

        when(covidAPIService.getResultForCityOrState(benchmark)).thenReturn(null);
        when(benchmarkRepository.save(benchmark)).thenReturn(benchmark);

        Benchmark result = benchmarkService.createBenchmark(benchmark);
        assertEquals(benchmark, result);
    }
    
    @Test
    public void testCreateBenchmarkThrowsHttpClientErrorException() {
    	Benchmark benchmark = new Benchmark(LocalDate.of(2020, 03, 18),LocalDate.of(2021, 11, 24),"region","São José dos Campos","São Paulo",null);
        
        assertThrows(HttpClientErrorException.class, () -> {
            benchmarkService.createBenchmark(benchmark);
        });
    }

    @Test
    public void testUpdateBenchmarkForCountry() {
        Benchmark benchmark = new Benchmark(LocalDate.of(2021, 03, 06),LocalDate.of(2021, 12, 18),"country","US","Brazil",null);

        when(benchmarkRepository.findById((long) 1)).thenReturn(Optional.of(benchmark));
        when(benchmarkRepository.save(benchmark)).thenReturn(benchmark);
        
        Benchmark result = benchmarkService.createBenchmark(benchmark);
        assertEquals(benchmark, result);
    }
    
    @Test
    public void testUpdateBenchmarkForCity() {
    	Benchmark benchmark = new Benchmark(LocalDate.of(2020, 03, 18),LocalDate.of(2021, 11, 24),"city","São José dos Campos","São Paulo",null);

        when(benchmarkRepository.findById((long) 1)).thenReturn(Optional.of(benchmark));
        when(benchmarkRepository.save(benchmark)).thenReturn(benchmark);
        
        Benchmark result = benchmarkService.createBenchmark(benchmark);
        assertEquals(benchmark, result);
    }
    
    @Test
    public void testUpdateBenchmarkThrowsHttpClientErrorException() {
    	Benchmark benchmark = new Benchmark(LocalDate.of(2020, 03, 18),LocalDate.of(2021, 11, 24),"region","São José dos Campos","São Paulo",null);
        
    	when(benchmarkRepository.findById((long) 1)).thenReturn(Optional.of(benchmark));
    	
        assertThrows(HttpClientErrorException.class, () -> {
            benchmarkService.updateBenchmarkById((long) 1, benchmark);
        });
    }
    
    
    @Test
    public void testDeleteBenchmarkByIdForCountry() {
        Long id = (long) 1;
        Benchmark benchmark = new Benchmark(LocalDate.of(2021, 03, 06),LocalDate.of(2021, 12, 18),"country","US","Brazil",null);
        
        when(benchmarkRepository.findById(id)).thenReturn(Optional.of(benchmark));

        benchmarkService.deleteBenchmarkById(id);

        verify(resultCountryRepository, times(1)).deleteByBenchmarkId(id);
        verify(benchmarkRepository, times(1)).delete(benchmark);
    }

}
