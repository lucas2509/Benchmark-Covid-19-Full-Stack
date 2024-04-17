package com.example.Covid19.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Model para representar os resultados do benchmark de dados da Covid-19 entre cidades ou estados.
 */
@Entity
@SuppressWarnings("unused")
public class ResultCityState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Dados do período inicial para cidade/estado 1
	private int start_cases_1;
    private int start_deaths__1;
    private int start_estimated_population_1;
    private float start_case_rate_1;
    private float start_death_rate_1;
    
    // Dados do período final para cidade/estado 1
    private int end_cases_1;
    private int end_deaths__1;
    private int end_estimated_population_1;
    private float end_case_rate_1;
    private float end_death_rate_1;
    
    // Dados do período inicial para cidade/estado 2
    private int start_cases_2;
    private int start_deaths__2;
    private int start_estimated_population_2;
    private float start_case_rate_2;
    private float start_death_rate_2;
    
    // Dados do período inicial para cidade/estado 2
    private int end_cases_2;
    private int end_deaths__2;
    private int end_estimated_population_2;
    private float end_case_rate_2;
    private float end_death_rate_2;
    
	public ResultCityState() {	
	}
	
	public ResultCityState(int start_cases_1, int start_cases_2, int start_deaths__1, int start_deaths__2, int end_cases_1, int end_cases_2, int end_deaths__1,
			 int end_deaths__2) {
		super();
		this.start_cases_1 = start_cases_1;
		this.start_deaths__1 = start_deaths__1;
		this.end_cases_1 = end_cases_1;
		this.end_deaths__1 = end_deaths__1;
		this.start_cases_2 = start_cases_2;
		this.start_deaths__2 = start_deaths__2;
		this.end_cases_2 = end_cases_2;
		this.end_deaths__2 = end_deaths__2;
	}
	
	public ResultCityState(int end_cases_1, int end_deaths__1, int end_estimated_population_1, float end_case_rate_1, float end_death_rate_1, 
				  int start_cases_1, int start_deaths__1, int start_estimated_population_1, float start_case_rate_1,float start_death_rate_1, 
				  int end_cases_2, int end_deaths_2, int end_estimated_population_2, float end_case_rate_2, float end_death_rate_2, 
				  int start_cases_2, int start_deaths_2, int start_estimated_population_2, float start_case_rate_2, float start_death_rate_2) {
		super();
		this.start_cases_1 = start_cases_1;
		this.start_deaths__1 = start_deaths__1;
		this.start_estimated_population_1 = start_estimated_population_1;
		this.start_case_rate_1 = start_case_rate_1;
		this.start_death_rate_1 = start_death_rate_1;
		this.end_cases_1 = end_cases_1;
		this.end_deaths__1 = end_deaths__1;
		this.end_estimated_population_1 = end_estimated_population_1;
		this.end_case_rate_1 = end_case_rate_1;
		this.end_death_rate_1 = end_death_rate_1;
		this.start_cases_2 = start_cases_2;
		this.start_deaths__2 = start_deaths_2;
		this.start_estimated_population_2 = start_estimated_population_2;
		this.start_case_rate_2 = start_case_rate_2;
		this.start_death_rate_2 = start_death_rate_2;
		this.end_cases_2 = end_cases_2;
		this.end_deaths__2 = end_deaths_2;
		this.end_estimated_population_2 = end_estimated_population_2;
		this.end_case_rate_2 = end_case_rate_2;
		this.end_death_rate_2 = end_death_rate_2;
	}

	public Long getId() {
		return id;
	}

	public int getStart_cases_1() {
		return start_cases_1;
	}

	public int getStart_deaths__1() {
		return start_deaths__1;
	}

	public int getStart_estimated_population_1() {
		return start_estimated_population_1;
	}

	public float getStart_case_rate_1() {
		return start_case_rate_1;
	}

	public float getStart_death_rate_1() {
		return start_death_rate_1;
	}

	public int getEnd_cases_1() {
		return end_cases_1;
	}

	public int getEnd_deaths__1() {
		return end_deaths__1;
	}

	public int getEnd_estimated_population_1() {
		return end_estimated_population_1;
	}

	public float getEnd_case_rate_1() {
		return end_case_rate_1;
	}

	public float getEnd_death_rate_1() {
		return end_death_rate_1;
	}

	public int getStart_cases_2() {
		return start_cases_2;
	}

	public int getStart_deaths__2() {
		return start_deaths__2;
	}

	public int getStart_estimated_population_2() {
		return start_estimated_population_2;
	}

	public float getStart_case_rate_2() {
		return start_case_rate_2;
	}

	public float getStart_death_rate_2() {
		return start_death_rate_2;
	}

	public int getEnd_cases_2() {
		return end_cases_2;
	}

	public int getEnd_deaths__2() {
		return end_deaths__2;
	}

	public int getEnd_estimated_population_2() {
		return end_estimated_population_2;
	}

	public float getEnd_case_rate_2() {
		return end_case_rate_2;
	}

	public float getEnd_death_rate_2() {
		return end_death_rate_2;
	}

	public void setStart_cases_1(int start_cases_1) {
		this.start_cases_1 = start_cases_1;
	}

	public void setStart_deaths__1(int start_deaths__1) {
		this.start_deaths__1 = start_deaths__1;
	}

	public void setStart_estimated_population_1(int start_estimated_population_1) {
		this.start_estimated_population_1 = start_estimated_population_1;
	}

	public void setStart_case_rate_1(float start_case_rate_1) {
		this.start_case_rate_1 = start_case_rate_1;
	}

	public void setStart_death_rate_1(float start_death_rate_1) {
		this.start_death_rate_1 = start_death_rate_1;
	}

	public void setEnd_cases_1(int end_cases_1) {
		this.end_cases_1 = end_cases_1;
	}

	public void setEnd_deaths__1(int end_deaths__1) {
		this.end_deaths__1 = end_deaths__1;
	}

	public void setEnd_estimated_population_1(int end_estimated_population_1) {
		this.end_estimated_population_1 = end_estimated_population_1;
	}

	public void setEnd_case_rate_1(float end_case_rate_1) {
		this.end_case_rate_1 = end_case_rate_1;
	}

	public void setEnd_death_rate_1(float end_death_rate_1) {
		this.end_death_rate_1 = end_death_rate_1;
	}

	public void setStart_cases_2(int start_cases_2) {
		this.start_cases_2 = start_cases_2;
	}

	public void setStart_deaths__2(int start_deaths__2) {
		this.start_deaths__2 = start_deaths__2;
	}

	public void setStart_estimated_population_2(int start_estimated_population_2) {
		this.start_estimated_population_2 = start_estimated_population_2;
	}

	public void setStart_case_rate_2(float start_case_rate_2) {
		this.start_case_rate_2 = start_case_rate_2;
	}

	public void setStart_death_rate_2(float start_death_rate_2) {
		this.start_death_rate_2 = start_death_rate_2;
	}

	public void setEnd_cases_2(int end_cases_2) {
		this.end_cases_2 = end_cases_2;
	}

	public void setEnd_deaths__2(int end_deaths__2) {
		this.end_deaths__2 = end_deaths__2;
	}

	public void setEnd_estimated_population_2(int end_estimated_population_2) {
		this.end_estimated_population_2 = end_estimated_population_2;
	}

	public void setEnd_case_rate_2(float end_case_rate_2) {
		this.end_case_rate_2 = end_case_rate_2;
	}

	public void setEnd_death_rate_2(float end_death_rate_2) {
		this.end_death_rate_2 = end_death_rate_2;
	}
}