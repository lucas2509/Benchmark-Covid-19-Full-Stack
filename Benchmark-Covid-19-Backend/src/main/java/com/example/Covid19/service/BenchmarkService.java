package com.example.Covid19.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import com.example.Covid19.repository.BenchmarkRepository;
import com.example.Covid19.repository.ResultCityStateRepository;
import com.example.Covid19.repository.ResultCountryRepository;
import com.example.Covid19.model.Benchmark;
import com.example.Covid19.model.ResultCityState;
import com.example.Covid19.model.ResultCountry;

/**
 * Serviços CRUD para o Benchmark
 * 
 * Utiliza os repositorios benchmarkRepository, resultCityStateRepository, resultCountryRepository para acesso ao banco de dados
 * Utiliza o serviço covidAPIService para obtenção de dados da Covid19
 */
@Service
public class BenchmarkService {
    private final BenchmarkRepository benchmarkRepository;
    private final ResultCityStateRepository resultCityStateRepository;
    private final ResultCountryRepository resultCountryRepository;
    private final CovidAPIService covidAPIService;

    @Autowired
    public BenchmarkService(BenchmarkRepository benchmarkRepository, ResultCityStateRepository resultRepository, ResultCountryRepository resultCountryRepository,CovidAPIService covidAPIService) {
        this.benchmarkRepository = benchmarkRepository;
        this.resultCityStateRepository = resultRepository;
        this.resultCountryRepository = resultCountryRepository;
        this.covidAPIService = covidAPIService;
    }

    // Retorna todos os benchmarks
    public List<Benchmark> getAllBenchmark() {
        return benchmarkRepository.findAll();
    }
    
    // Retorna um benchmark pelo ID
    public Benchmark getBenchmarkById(Long id) {
        return benchmarkRepository.findById(id).orElseThrow();
    }
    
    // Retorna a id de um benchmark através dos parametros
    public Long findIdByParameters(LocalDate startDate,LocalDate endDate,String placeType, String placeName1, String placeName2) {
    	Long benchmarkId = benchmarkRepository.findIdByAttributes(startDate,endDate,placeType,placeName1,placeName2);

        if(benchmarkId == null)throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        return benchmarkId;
    }

    // Cria um novo benchmark
    @Transactional
    public Benchmark createBenchmark(Benchmark benchmark) {
        // Verifica se o tipo de lugar é country
    	// Obtem uma lista de resultados para o benchmark entre paises(resultCountry) e salva todos os registros
        if(benchmark.getPlace_type().equals("country")) {
            List<ResultCountry> resultCountry = covidAPIService.getResultForCountry(benchmark);
            
            resultCountryRepository.saveAll(resultCountry);
        } 
        // Verifica se o tipo de lugar é city ou statee
    	// Obtem o resultado para o benchmark entre ciddades ou estado(ResultCityState) e salva todos os registros
        else if(benchmark.getPlace_type().equals("city") || benchmark.getPlace_type().equals("state")) {
			ResultCityState result = covidAPIService.getResultForCityOrState(benchmark);
			
			resultCityStateRepository.save(result);
			benchmark.setResult(result);
        } else throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        
        return benchmarkRepository.save(benchmark);
    }
    
    // Atualiza um benchmark pelo ID
    @Transactional
    public Benchmark updateBenchmarkById(Long id, Benchmark updatedBenchmark) {
        Benchmark benchmark = benchmarkRepository.findById(id).orElseThrow();
        
        // Verifica se o tipo de lugar é country
        // Atualiza todos os registros ResultCountry do benchmark
        if(benchmark.getPlace_type().equals("country")) {
            resultCountryRepository.deleteByBenchmarkId(id);
            
            // Atualiza os dados do benchmark com os novos dados
            BeanUtils.copyProperties(updatedBenchmark, benchmark);

            List<ResultCountry> resultCountry = covidAPIService.getResultForCountry(benchmark);
            resultCountryRepository.saveAll(resultCountry);

            return benchmarkRepository.save(benchmark);
        }
        // Verifica se o tipo de lugar é city ou state
        // Atualiza o registro ResultCityState do benchmark
        else if(benchmark.getPlace_type().equals("city") || benchmark.getPlace_type().equals("state")) {
			ResultCityState newResult = covidAPIService.getResultForCityOrState(updatedBenchmark);
			
			BeanUtils.copyProperties(newResult, benchmark.getResult());
			resultCityStateRepository.save(benchmark.getResult());
			updatedBenchmark.setResult(benchmark.getResult());

			// Atualiza os dados do benchmark com os novos dados
	        BeanUtils.copyProperties(updatedBenchmark, benchmark);
	        return benchmarkRepository.save(benchmark);
        }
        else throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    // Exclui um benchmark pelo ID
    @Transactional
    public void deleteBenchmarkById(Long id) {
        Benchmark benchmark = benchmarkRepository.findById(id).orElseThrow();
        
        // Se for do tipo country deletar todos os registros de ResultCountry
        if(benchmark.getPlace_type().equals("country")) 
        	resultCountryRepository.deleteByBenchmarkId(id);
        // Se for do tipo city ou statees o registro de ResultCityStatete
        else if(benchmark.getPlace_type().equals("city") || benchmark.getPlace_type().equals("state"))
				resultCityStateRepository.delete(benchmark.getResult());
        else throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        
        benchmarkRepository.delete(benchmark);
    }   
}
