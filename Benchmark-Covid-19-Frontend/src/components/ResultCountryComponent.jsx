import { useEffect, useState } from 'react';

import BenchmarkService from '../service/BenchmarkService';
import LineGraph from './graph/LineGraph';
import StakedColumnsGraph from './graph/StakedColumnsGraph';

//Componente que exibe o resultado do Benchmark entre paises
export default function ResultCountryComponent({benchmark}){
  const [benchmarkData, setBenchmarkData] = useState([]);
  const [results, setResults] = useState([]);
  const [resultsCasesContry1, setCasesCountry1] = useState([]);
  const [resultsCasesContry2, setCasesCountry2] = useState([]);
  const [resultsDeathsContry1, setDeathsCountry1] = useState([]);
  const [resultsDeathsContry2, setDeathsCountry2] = useState([]);
  const [resultsSumary, setResultsSumary] = useState([]);
  
  useEffect(() => {
    async function fetchBenchmarks () {
      try {
        if (benchmark) {
          setBenchmarkData(benchmark);

          if (benchmark.place_type === "country") {
            setResults(await BenchmarkService.getResultCountryByBenchmarkId(benchmark.id));
          }
          if (benchmark.place_type === "city" || benchmark.place_type === "state") {
            setResults(await BenchmarkService.getResultCityStateByBenchmarkId(benchmark.id));
          }
        }
      } catch (error) {
        console.error('Erro ao obter os resultados:', error);
      }
    };

    fetchBenchmarks();
  }, [benchmark]);

  useEffect(() => {
    var filtered;
    var mappedResults;

    filtered = results.filter(obj => obj.type === "cases" && obj.country_number === 1);
    mappedResults = filtered.map(obj => ({
      x: obj.date,
      y: obj.cases
    }));
    setCasesCountry1(mappedResults);

    filtered = results.filter(obj => obj.type === "cases" && obj.country_number === 2);
    mappedResults = filtered.map(obj => ({
      x: obj.date,
      y: obj.cases
    }));
    setCasesCountry2(mappedResults);

    filtered = results.filter(obj => obj.type === "deaths" && obj.country_number === 1);
    mappedResults = filtered.map(obj => ({
      x: obj.date,
      y: obj.cases
    }));
    setDeathsCountry1(mappedResults);

    filtered = results.filter(obj => obj.type === "deaths" && obj.country_number === 2);
    mappedResults = filtered.map(obj => ({
      x: obj.date,
      y: obj.cases
    }));
    setDeathsCountry2(mappedResults);

    ////////////////////////////////
    let list = [];
    let filteredObject; 

    filteredObject = results.find(obj =>
      obj.date === benchmarkData.start_date &&
      obj.type === "deaths" &&
      obj.country_number === 1
    );
    if(filteredObject) list.push(filteredObject.cases);

    filteredObject = results.find(obj =>
      obj.date === benchmarkData.end_date &&
      obj.type === "deaths" &&
      obj.country_number === 1
    );
    if(filteredObject) list.push(filteredObject.cases);

    filteredObject = results.find(obj =>
      obj.date === benchmarkData.start_date &&
      obj.type === "deaths" &&
      obj.country_number === 2
    );
    if(filteredObject) list.push(filteredObject.cases);

    filteredObject = results.find(obj =>
      obj.date === benchmarkData.end_date &&
      obj.type === "deaths" &&
      obj.country_number === 2
    );
    if(filteredObject) list.push(filteredObject.cases);
    
    /////////////////////////////////////////
    
    filteredObject = results.find(obj =>
      obj.date === benchmarkData.start_date &&
      obj.type === "cases" &&
      obj.country_number === 1
    );
    if(filteredObject) list.push(filteredObject.cases);

    filteredObject = results.find(obj =>
      obj.date === benchmarkData.end_date &&
      obj.type === "cases" &&
      obj.country_number === 1
    );
    if(filteredObject) list.push(filteredObject.cases);

    filteredObject = results.find(obj =>
      obj.date === benchmarkData.start_date &&
      obj.type === "cases" &&
      obj.country_number === 2
    );
    if(filteredObject) list.push(filteredObject.cases);

    filteredObject = results.find(obj =>
      obj.date === benchmarkData.end_date &&
      obj.type === "cases" &&
      obj.country_number === 2
    );
    if(filteredObject) list.push(filteredObject.cases);


    setResultsSumary(list);

  }, [results]);


  return (
    <div>
      <div style={{ marginBottom: '20px', background: '#fff', borderRadius: '10px', boxShadow: '0 0 20px rgba(0, 0, 0, 0.5)', padding: '20px' }}>
        <LineGraph graphName={"Gráfico de Casos"} graphSerieName1={benchmarkData.place_name_1} graphData1={resultsCasesContry1} graphSerieName2={benchmarkData.place_name_2} graphData2={resultsCasesContry2} />
      </div>
      <div style={{ marginBottom: '20px', background: '#fff', borderRadius: '10px', boxShadow: '0 0 20px rgba(0, 0, 0, 0.5)', padding: '20px' }}>
        <LineGraph graphName={"Gráfico de Mortes"} graphSerieName1={benchmarkData.place_name_1} graphData1={resultsDeathsContry1} graphSerieName2={benchmarkData.place_name_2} graphData2={resultsDeathsContry2} />
      </div>
      <div style={{ marginBottom: '20px', background: '#fff', borderRadius: '10px', boxShadow: '0 0 20px rgba(0, 0, 0, 0.5)', padding: '20px' }}>
        <StakedColumnsGraph resultsSumary={resultsSumary} benchmarkData={benchmarkData}/>
      </div>
    </div>
  );
};