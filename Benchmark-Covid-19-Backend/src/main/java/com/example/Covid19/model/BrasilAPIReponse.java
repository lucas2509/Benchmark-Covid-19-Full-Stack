package com.example.Covid19.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model para representar o response da API Brasil
 */
public class BrasilAPIReponse {
	// Lista de resultados de dados COVID-19
    private List<CovidDataResult> results;
    
    public BrasilAPIReponse() {
	}

	public List<CovidDataResult> getResults() {
		return results;
	}
	
	public static class CovidDataResult {
		// Número total de casos confirmados
	    private int confirmed;
	    // Taxa de casos confirmados por 100 mil habitantes
	    @JsonProperty("confirmed_per_100k_inhabitants")
	    private float confirmedPer100kInhabitants;
	    
	    // Data do dado
	    private String date;
	    
	    // Taxa de mortalidade
	    @JsonProperty("death_rate")
	    private float deathRate;
	    // Número de mortes
	    private int deaths;
	    
	    // População estimada
	    @JsonProperty("estimated_population")
	    private int estimatedPopulation;
	    
	    public CovidDataResult() {
			// TODO Auto-generated constructor stub
		}
	    
	    public int getConfirmed() {
			return confirmed;
		}
	    
	    public String getDate() {
			return date;
		}

		public float getConfirmedPer100kInhabitants() {
			return confirmedPer100kInhabitants;
		}

		public float getDeathRate() {
			return deathRate;
		}

		public int getDeaths() {
			return deaths;
		}

		public int getEstimatedPopulation() {
			return estimatedPopulation;
		}

	}

}


