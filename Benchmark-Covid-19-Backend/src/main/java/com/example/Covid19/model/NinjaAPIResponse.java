package com.example.Covid19.model;

import java.time.LocalDate;
import java.util.Map;

/**
 * Model para representar o response da API Ninja
 */
public class NinjaAPIResponse {
	//Nome do país
    private String country;
    
    // Map que relaciona uma data ao número de casos
    private Map<LocalDate, CasesData> cases;
    // Map que relaciona uma data ao número de mortes
    private Map<LocalDate, CasesData> deaths;
    
    // Dados da quantidade de casos ou morte
    public static class CasesData {
    	//Número de casos ou mortos no dia
        private int total;
        
        public int getTotal() {
            return total;
        }
    }
    
    public NinjaAPIResponse() {
    }

    public String getCountry() {
        return country;
    }

    public Map<LocalDate, CasesData> getCases() {
        return cases;
    }
    
    public Map<LocalDate, CasesData> getDeaths() {
        return deaths;
    }
}