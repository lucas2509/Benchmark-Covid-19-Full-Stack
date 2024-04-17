package com.example.Covid19.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Model para representar os resultados do benchmark de dados da Covid-19 entre países.
 */
@Entity
@SuppressWarnings("unused")
public class ResultCountry {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	// Número que representa o país no benchmark
	private int country_number;
	// Tipo de dados da Covid-19 (cases ou deaths) 
	private String type;
	// Data do dado
	private LocalDate date;
	// Número de casos ou mortes
	private int cases;
	
	@ManyToOne
    @JoinColumn(name = "benchmark_id")
	private Benchmark benchmark;
	
	public ResultCountry() {
	}

	
	public ResultCountry(int country_number, String type, LocalDate date, int cases, Benchmark benchmark) {
		super();
		this.country_number = country_number;
		this.type = type;
		this.date = date;
		this.cases = cases;
		this.benchmark = benchmark;
	}


	public Long getId() {
		return id;
	}


	public int getCountry_number() {
		return country_number;
	}


	public String getType() {
		return type;
	}


	public LocalDate getDate() {
		return date;
	}


	public int getCases() {
		return cases;
	}

}
