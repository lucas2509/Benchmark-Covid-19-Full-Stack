package com.example.Covid19.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Covid19.model.Benchmark;

@Repository
public interface BenchmarkRepository extends JpaRepository<Benchmark, Long> {

	@Query("SELECT benchmark.id FROM Benchmark benchmark WHERE benchmark.start_date = :startDate AND benchmark.end_date = :endDate AND benchmark.place_type = :placeType AND benchmark.place_name_1 = :placeName1 AND benchmark.place_name_2 = :placeName2")
    Long findIdByAttributes(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("placeType") String placeType, @Param("placeName1") String placeName1, @Param("placeName2") String placeName2);
}
