package com.example.Covid19.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.Covid19.config.APIConfig;
import com.example.Covid19.model.Benchmark;
import com.example.Covid19.model.BrasilAPIReponse;
import com.example.Covid19.model.BrasilAPIReponse.CovidDataResult;
import com.example.Covid19.model.NinjaAPIResponse.CasesData;
import com.example.Covid19.model.NinjaAPIResponse;
import com.example.Covid19.model.ResultCityState;
import com.example.Covid19.model.ResultCountry;

/**
 * Serviços de obtenção de dados sobre a Covid19
 * 
 * Utiliza as configurações restTemplate e apiConfig para acessar as API's
 */
@Service
public class CovidAPIService {
    private final RestTemplate restTemplate;
    private final APIConfig apiConfig;
	
    public CovidAPIService(RestTemplate restTemplate, APIConfig apiConfig) {
        this.restTemplate = restTemplate;
        this.apiConfig = apiConfig;
    }
    
    // Obtém resultados de um benchmark entre paises
    public List<ResultCountry> getResultForCountry(Benchmark benchmark) {
        List<ResultCountry> resultCountry = new ArrayList<>();
        List<NinjaAPIResponse> listaNinjaAPIResponse = new ArrayList<>();
        
        // Obtém dados de casos e mortes para o primeiro local a partir da API Ninja
        listaNinjaAPIResponse.addAll(getNinjaAPIResponse(benchmark.getStart_date(), benchmark.getEnd_date(), "cases", benchmark.getPlace_name_1()));
        listaNinjaAPIResponse.addAll(getNinjaAPIResponse(benchmark.getStart_date(), benchmark.getEnd_date(), "deaths", benchmark.getPlace_name_1()));
        resultCountry.addAll(resultCountryList(1, benchmark, listaNinjaAPIResponse));

        listaNinjaAPIResponse = new ArrayList<>();
        
        // Obtém dados de casos e mortes para o segundo local a partir da API Ninja
        listaNinjaAPIResponse.addAll(getNinjaAPIResponse(benchmark.getStart_date(), benchmark.getEnd_date(), "cases", benchmark.getPlace_name_2()));
        listaNinjaAPIResponse.addAll(getNinjaAPIResponse(benchmark.getStart_date(), benchmark.getEnd_date(), "deaths", benchmark.getPlace_name_2()));
        resultCountry.addAll(resultCountryList(2, benchmark, listaNinjaAPIResponse));
        
        return resultCountry;
    }
    
    // Obtém resultados de um benchmark entre cidades ou estados
    public ResultCityState getResultForCityOrState(Benchmark benchmark) {
        ResultCityState result = null;

        // Encontra os registros que correspondem com a data inicial ou data final aproximada do benchmark
        List<CovidDataResult> matchingResponses = findMatchCityState(benchmark);
        
        // Cria um objeto do tipo ResultCityState a partir dos 4 registros encontrados na seguinte ordem:
        // (Data final / Pais 1), (Data inicial / Pais 1), (Data final / Pais 2) e (Data inicial / Pais 2)
        if (matchingResponses.size() == 4) {
            result = new ResultCityState(
                matchingResponses.get(0).getConfirmed(),
                matchingResponses.get(0).getDeaths(),
                matchingResponses.get(0).getEstimatedPopulation(),
                matchingResponses.get(0).getConfirmedPer100kInhabitants() / 100000,
                matchingResponses.get(0).getDeathRate(),
                matchingResponses.get(1).getConfirmed(),
                matchingResponses.get(1).getDeaths(),
                matchingResponses.get(1).getEstimatedPopulation(),
                matchingResponses.get(1).getConfirmedPer100kInhabitants() / 100000,
                matchingResponses.get(1).getDeathRate(),
                matchingResponses.get(2).getConfirmed(),
                matchingResponses.get(2).getDeaths(),
                matchingResponses.get(2).getEstimatedPopulation(),
                matchingResponses.get(2).getConfirmedPer100kInhabitants() / 100000,
                matchingResponses.get(2).getDeathRate(),
                matchingResponses.get(3).getConfirmed(),
                matchingResponses.get(3).getDeaths(),
                matchingResponses.get(3).getEstimatedPopulation(),
                matchingResponses.get(3).getConfirmedPer100kInhabitants() / 100000,
                matchingResponses.get(3).getDeathRate());
        } 
        else throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        
        //Ajuste para caso de os resultados serem de datas aproximadas
        benchmark.setStart_date(matchingResponses.get(0).getDate());
        benchmark.setEnd_date(matchingResponses.get(2).getDate());

        return result;
    }

	// Obtém os resultados para as datas inicial e final do benchmark dos locais 1 e 2
	public  List<CovidDataResult> findMatchCityState(Benchmark benchmark) {
		int count;
		Boolean isDateFound;
		List<CovidDataResult> covidDataResultList = new ArrayList<>();
		List<CovidDataResult> matchingResponses = new ArrayList<>();

		// Obtém os dados da Covid19 para os locais definidos no benchmark
		covidDataResultList.addAll(getBrasilAPIResponse(benchmark.getPlace_type(), benchmark.getPlace_name_1()).getResults());
		covidDataResultList.addAll(getBrasilAPIResponse(benchmark.getPlace_type(), benchmark.getPlace_name_2()).getResults());

		// Cria uma nova lista de resultados ordenada decresente em relacao a data
		List<CovidDataResult> covidDataResultListReversed = new ArrayList<>(covidDataResultList);
		Collections.sort(covidDataResultListReversed, Comparator.comparing(CovidDataResult::getDate).reversed());
		isDateFound = false;
		count = 0;
		// Percorre a lista para para procurar uma data aproximada da data inicial do Benchmark
		while(!isDateFound && count < covidDataResultListReversed.size() - 1) {
			LocalDate auxDate = covidDataResultListReversed.get(count).getDate();
			LocalDate nextDate = covidDataResultListReversed.get(count + 1).getDate();

			if(!auxDate.isAfter(benchmark.getStart_date()) && auxDate.isEqual(nextDate)) {
				matchingResponses.add(covidDataResultListReversed.get(count));
				matchingResponses.add(covidDataResultListReversed.get(count + 1));
				isDateFound = true;
			}
			count++;
		}

		// Cria uma nova lista de resultados ordenada crescente em relacao a data
		List<CovidDataResult> covidDataResultListOrdened = new ArrayList<>(covidDataResultList);
		Collections.sort(covidDataResultListOrdened, Comparator.comparing(CovidDataResult::getDate));
		isDateFound = false;
		count = 0;
		// Percorre a lista para para procurar uma data aproximada da data final do Benchmark
		while(!isDateFound && count < covidDataResultListOrdened.size() - 1) {
			LocalDate auxDate = covidDataResultListOrdened.get(count).getDate();
			LocalDate nextDate = covidDataResultListOrdened.get(count + 1).getDate();
	
			if(!auxDate.isBefore(benchmark.getEnd_date()) && auxDate.isEqual(nextDate)) {
				matchingResponses.add(covidDataResultListOrdened.get(count));
				matchingResponses.add(covidDataResultListOrdened.get(count + 1));
				isDateFound = true;
			}
			count++;
		}

	return matchingResponses;
    }
   
    // Obtém resposta da API Brasil.IO para um local específico
    // @param type : Uma string que assume "city" ou "state"
    public BrasilAPIReponse getBrasilAPIResponse(String type, String place){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiConfig.getBrasilUrl())
                .queryParam("place_type", type);
        String apiUrl = builder.toUriString();
        apiUrl += "&" + type + "=" + place;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiConfig.getBrasilToken());
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BrasilAPIReponse> responseEntity = restTemplate.exchange(
                apiUrl, HttpMethod.GET, requestEntity, BrasilAPIReponse.class);
        
        if (!responseEntity.getStatusCode().is2xxSuccessful()) 
        	throw new HttpClientErrorException(responseEntity.getStatusCode(), "Request Error"); 
        
        return responseEntity.getBody();
    }
    
    // Obtém resposta da API Ninja para um país específico
    // @param type : Uma string que assume "cases" ou "deaths"
    public List<NinjaAPIResponse> getNinjaAPIResponse(LocalDate startDate, LocalDate endDate, String type, String country) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiConfig.getNinjaUrl())
                 .queryParam("country", country)
                 .queryParam("type", type);
         String apiUrl = builder.toUriString();

         HttpHeaders headers = new HttpHeaders();
         headers.set("X-Api-Key", apiConfig.getNinjaToken());
         HttpEntity<?> requestEntity = new HttpEntity<>(headers);

         ResponseEntity<NinjaAPIResponse[]> responseEntity = restTemplate.exchange(
                 apiUrl,
                 HttpMethod.GET,
                 requestEntity,
                 NinjaAPIResponse[].class
         );
         
         if (!responseEntity.getStatusCode().is2xxSuccessful()) 
        	 throw new HttpClientErrorException(responseEntity.getStatusCode(), "Request Error");
 
         
         // Converte a resposta em uma lista
         NinjaAPIResponse[] responseArray = responseEntity.getBody();
         List<NinjaAPIResponse> responseList = new ArrayList<>();
         for (NinjaAPIResponse response : responseArray) {
             responseList.add(response);
         }

         if (!responseList.isEmpty()) return responseList;
         else return null;
     }

    // Processa a lista de respostas da API Ninja e retorna uma lista de ResultCountry
    // @param countryNumber : Um inteiro que representa o pais de número 1 ou 2
    public List<ResultCountry> resultCountryList(int countryNumber, Benchmark benchmark, List<NinjaAPIResponse> listaNinjaAPIResponse){
        List<ResultCountry> resultCountryList = new ArrayList<>();
        
        //Adiciona dados da lista listaNinjaAPIResponse a lista resultCountryList em relacao ao período de tempo entre a data inicial e final do benchmark
        for (NinjaAPIResponse response : listaNinjaAPIResponse) {
            String type = null;
            
            // Determina o tipo de dados (casos ou mortes)
            if (response.getCases() != null) type = "cases";
            if (response.getDeaths() != null) type = "deaths";
            
            if (type.equals("cases")) {
                for (LocalDate date : response.getCases().keySet()) {
                	// Adiciona o registro de casesData caso a data do registro esteja contida entre a data inicial e final do benchmark
                    CasesData casesData = response.getCases().get(date);
                    if (date.compareTo(benchmark.getStart_date()) >= 0 && date.compareTo(benchmark.getEnd_date()) <= 0)
                        resultCountryList.add(new ResultCountry(countryNumber, type, date, casesData.getTotal(), benchmark));
                }
            }
            if (type.equals("deaths")) {
            	for (LocalDate date : response.getDeaths().keySet()) {
            		// Adiciona o registro de casesData caso a data do registro esteja contida entre a data inicial e final do benchmark
            		CasesData casesData = response.getDeaths().get(date);
                    if (date.compareTo(benchmark.getStart_date()) >= 0 && date.compareTo(benchmark.getEnd_date()) <= 0)
                        resultCountryList.add(new ResultCountry(countryNumber, type, date, casesData.getTotal(), benchmark));
                }
            }
            
        }
        
        return resultCountryList;
    }
    
}
