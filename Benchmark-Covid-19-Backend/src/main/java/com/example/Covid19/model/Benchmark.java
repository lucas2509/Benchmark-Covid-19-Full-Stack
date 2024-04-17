package com.example.Covid19.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

/**
 * Model para representar os parametros do benchmark de dados da Covid-19.
 */
@Entity
@JsonIgnoreProperties({"result"})
public class Benchmark {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	// Data de início do benchmark
	@NotNull
    private LocalDate start_date;
	// Data de término do benchmark
	@NotNull
    private LocalDate end_date;
    
	// Tipo de local
	@NotNull
    private String place_type;
    
    // Nome do local 1
    @NotNull
    private String place_name_1;
    // Nome do local 2
    @NotNull
    private String place_name_2;
    
    @OneToOne
    @JoinColumn(name = "result_id")
    private ResultCityState result;
    
    
    @AssertTrue
    private boolean isStartDateBeforeEndDate() {
        return start_date.isBefore(end_date);
    }
    
    public Benchmark() {
		
	}
    
    public Benchmark(LocalDate start_date, LocalDate end_date, String place_type, String place_name_1,
			String place_name_2, ResultCityState result) {
		super();
		this.start_date = start_date;
		this.end_date = end_date;
		this.place_type = place_type;
		this.place_name_1 = place_name_1;
		this.place_name_2 = place_name_2;
		this.result = result;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public String getPlace_type() {
		return place_type;
	}

	public String getPlace_name_1() {
		return place_name_1;
	}

	public String getPlace_name_2() {
		return place_name_2;
	}

	public ResultCityState getResult() {
		return result;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public void setPlace_type(String place_type) {
		this.place_type = place_type;
	}

	public void setPlace_name_1(String place_name_1) {
		this.place_name_1 = place_name_1;
	}

	public void setPlace_name_2(String place_name_2) {
		this.place_name_2 = place_name_2;
	}

	public void setResult(ResultCityState result) {
		this.result = result;
	}
}