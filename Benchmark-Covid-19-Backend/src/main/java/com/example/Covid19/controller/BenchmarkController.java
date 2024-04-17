package com.example.Covid19.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.example.Covid19.model.Benchmark;
import com.example.Covid19.model.ResultCityState;
import com.example.Covid19.model.ResultCountry;
import com.example.Covid19.service.BenchmarkService;
import com.example.Covid19.service.ResultCityStateService;
import com.example.Covid19.service.ResultCountryService;

import jakarta.validation.Valid;

/**
 * Controlador para operações relacionadas a benchmarks
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/benchmark")
public class BenchmarkController {
    private final BenchmarkService benchmarkService;
    private final ResultCityStateService resultCityStateService;
    private final ResultCountryService resultCountryService;
    
    @Autowired
    public BenchmarkController(BenchmarkService productService, ResultCityStateService resultCityStateService, ResultCountryService resultCountryService) {
        this.benchmarkService = productService;
        this.resultCityStateService = resultCityStateService;
        this.resultCountryService = resultCountryService;
    }

    //Obtem todos os benchmarks
    @GetMapping
    public ResponseEntity<List<Benchmark>> getAllBenchmark() {
        List<Benchmark> benchmarks = benchmarkService.getAllBenchmark();
        
        if (!benchmarks.isEmpty()) return ResponseEntity.ok(benchmarks);
        else return ResponseEntity.noContent().build();
    }
    
    //Obtem um benchmark pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Benchmark> getBenchmarkById(@PathVariable Long id) {
        try {
            Benchmark benchmark = benchmarkService.getBenchmarkById(id);
            return ResponseEntity.ok(benchmark);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } 
    }

    //Obtem a ID de um benchmark através dos parametros
    @GetMapping("/id")
    public ResponseEntity<Long> findIdByParameters(
    		@RequestParam(name = "start_date", required = true) LocalDate startDate,
    	    @RequestParam(name = "end_date", required = true) LocalDate endDate,
    	    @RequestParam(name = "place_type", required = true) String placeType,
    	    @RequestParam(name = "place_name_1", required = true) String placeName1,
    	    @RequestParam(name = "place_name_2", required = true) String placeName2) {
        try {
            Long id = benchmarkService.findIdByParameters(startDate,endDate,placeType,placeName1,placeName2);
            return ResponseEntity.ok(id);
        }
        catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    //Cria um novo benchmark
    @PostMapping
    public ResponseEntity<Benchmark> createBenchmark(@RequestBody @Valid Benchmark benchmark) {
        try {
            Benchmark createdBenchmark = benchmarkService.createBenchmark(benchmark);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBenchmark);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } 
        catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    //Atualiza um benchmark pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<Benchmark> updateBenchmark(@PathVariable Long id, @RequestBody Benchmark updatedBenchmark) {
        try {
            Benchmark updatedEntity = benchmarkService.updateBenchmarkById(id, updatedBenchmark);
            
            if (updatedEntity != null) return ResponseEntity.ok(updatedEntity); 
            else return ResponseEntity.notFound().build(); 
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } 
        catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    //Exclui um benchmark pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBenchmark(@PathVariable Long id) {
        try {
            benchmarkService.deleteBenchmarkById(id);
            return ResponseEntity.noContent().build();
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } 
    }

    //Obtem o resultado de um benchmark entre cidades ou estados a partir da id do benchmark
    @GetMapping("/{id}/result/citystate")
    public ResponseEntity<ResultCityState> getResultCityStateByBenchmarkId(@PathVariable Long id) {
        try {
            Benchmark benchmark = benchmarkService.getBenchmarkById(id);
            ResultCityState resultCityState = resultCityStateService.getResultCityStateById(benchmark.getResult().getId()); 
            
            return ResponseEntity.ok(resultCityState);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } 
        catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Obtem os resultados de um benchmark entre paises a partir da id do benchmark
    @GetMapping("/{id}/result/country")
    public ResponseEntity<List<ResultCountry>> getResultCountryByBenchmarkId(@PathVariable Long id) {
        List<ResultCountry> resultCountries = resultCountryService.getAllResultCountryByBenchmarkId(id);

        if (!resultCountries.isEmpty()) return ResponseEntity.ok(resultCountries);
        else return ResponseEntity.noContent().build();
    }
}
