import { useEffect, useState } from 'react';
import BenchmarkService from '../service/BenchmarkService';
import DonutGraph from './graph/DonutGraph';
import BarGraph from './graph/BarGraph';

export default function ResultityStateComponent({benchmark}){
    const [result, setResult] = useState([]);
    const [series1, setSeries1] = useState([]);
    const [series2, setSeries2] = useState([]);

    useEffect(() => {
        const fetchBenchmarks = async () => {
          try {
            if (benchmark) {
              if (benchmark.place_type === "city" || benchmark.place_type === "state") {
                setResult(await BenchmarkService.getResultCityStateByBenchmarkId(benchmark.id));
              }
            }
          } catch (error) {
            console.error('Erro ao obter os resultados:', error);
          }

          const data = [{
            name: 'Casos : '+benchmark.place_name_1,
            data: [benchmark.start_cases_1, benchmark.start_cases_1]
          }, {
            name: 'Casos : '+benchmark.place_name_2,
            data: [76, 85]
          }, {
            name: 'Mortes : '+benchmark.place_name_1,
            data: [35, 41]
          },{
            name: 'Mortes : '+benchmark.place_name_2,
            data: [35, 41]
          }];

          setSeries(data);
        };
    
        fetchBenchmarks();
    }, [benchmark]);

    useEffect(() => {
        const fetchBenchmarks = async () => {
          if(result){
            var data;

            data = [{
                name: 'Casos : '+benchmark.place_name_1,
                data: [result.start_cases_1]
            }, {
                name: 'Casos : '+benchmark.place_name_2,
                data: [result.start_cases_2]
            }, {
                name: 'Mortes : '+benchmark.place_name_1,
                data: [result.start_deaths__1]
            },{
                name: 'Mortes : '+benchmark.place_name_2,
                data: [result.start_deaths__2]
            },{
                name: '.',
                data: [1],
            }];

            setSeries1(data);

            data = [{
                name: 'Casos : '+benchmark.place_name_1,
                data: [result.end_cases_1]
            }, {
                name: 'Casos : '+benchmark.place_name_2,
                data: [ result.end_cases_2]
            }, {
                name: 'Mortes : '+benchmark.place_name_1,
                data: [ result.end_deaths__1]
            },{
                name: 'Mortes : '+benchmark.place_name_2,
                data: [result.end_deaths__2],
            },{
                name: '.',
                data: [1],
            }];

            setSeries2(data);
          }
        };
    
        fetchBenchmarks();
    }, [result]);

    return (
      <div>
        <div style={{ marginBottom: '20px', background: '#fff', borderRadius: '10px', boxShadow: '0 0 20px rgba(0, 0, 0, 0.5)', padding: '20px' }}>
          <DonutGraph title={"Quantidade de habitantes"} series={[result.end_estimated_population_1, result.end_estimated_population_2]} label={[benchmark.place_name_1,benchmark.place_name_2]}/>
        </div>
        <div style={{ marginBottom: '20px', background: '#fff', borderRadius: '10px', boxShadow: '0 0 20px rgba(0, 0, 0, 0.5)', padding: '20px' }}>
          <BarGraph title={"Casos e Mortes por Covid-19"}  series={series1} categorias={['Data inicial : '+benchmark.start_date]} />
        </div>
        <div style={{ marginBottom: '20px', background: '#fff', borderRadius: '10px', boxShadow: '0 0 20px rgba(0, 0, 0, 0.5)', padding: '20px' }}>
          <BarGraph title={"Casos e Mortes por Covid-19"}  series={series2} categorias={['Data final : '+benchmark.start_date]} />
        </div>
        <div style={{ marginBottom: '20px', background: '#fff', borderRadius: '10px', boxShadow: '0 0 20px rgba(0, 0, 0, 0.5)', padding: '20px' }}>
          <h1 style={{ borderBottom: '2px solid #ccc', paddingBottom: '20px', fontSize: '24px', marginBottom: '10px' }}>Resultados</h1>
          <div style={{ borderBottom: '2px solid #ccc', marginBottom: '20px', paddingBottom: '20px' }}>
            <h2 style={{ fontSize: '20px', marginBottom: '10px' }}>Data Inicial: {benchmark.start_date}</h2>
            <h3 style={{ fontSize: '18px', marginBottom: '10px' }}>População estimada</h3>
            <p style={{ fontSize: '16px', marginBottom: '10px' }}>{result.start_estimated_population_1} - {result.start_estimated_population_2}</p>
            <h3 style={{ fontSize: '18px', marginBottom: '10px' }}>Quantidade de casos</h3>
            <p style={{ fontSize: '16px', marginBottom: '10px' }}>{result.start_cases_1} - {result.start_cases_2}</p>
            <h3 style={{ fontSize: '18px', marginBottom: '10px' }}>Quantidade de mortos</h3>
            <p style={{ fontSize: '16px', marginBottom: '10px' }}>{result.start_deaths__1} - {result.start_deaths__2}</p>
            <h3 style={{ fontSize: '18px', marginBottom: '10px' }}>Taxa de casos</h3>
            <p style={{ fontSize: '16px', marginBottom: '10px' }}>{result.start_case_rate_1} - {result.start_case_rate_2}</p>
            <h3 style={{ fontSize: '18px', marginBottom: '10px' }}>Taxa de mortes</h3>
            <p style={{ fontSize: '16px', marginBottom: '10px' }}>{result.start_death_rate_1} - {result.start_death_rate_2}</p>
          </div>
          <div>
            <h2 style={{ fontSize: '20px', marginBottom: '10px' }}>Data Final: {benchmark.end_date}</h2>
            <h3 style={{ fontSize: '18px', marginBottom: '10px' }}>População estimada</h3>
            <p style={{ fontSize: '16px', marginBottom: '10px' }}>{result.end_estimated_population_1} - {result.end_estimated_population_2}</p>
            <h3 style={{ fontSize: '18px', marginBottom: '10px' }}>Quantidade de casos</h3>
            <p style={{ fontSize: '16px', marginBottom: '10px' }}>{result.end_cases_1} - {result.end_cases_2}</p>
            <h3 style={{ fontSize: '18px', marginBottom: '10px' }}>Quantidade de mortos</h3>
            <p style={{ fontSize: '16px', marginBottom: '10px' }}>{result.end_deaths__1} - {result.end_deaths__2}</p>
            <h3 style={{ fontSize: '18px', marginBottom: '10px' }}>Taxa de casos</h3>
            <p style={{ fontSize: '16px', marginBottom: '10px' }}>{result.end_case_rate_1} - {result.end_case_rate_2}</p>
            <h3 style={{ fontSize: '18px', marginBottom: '10px' }}>Taxa de mortes</h3>
            <p style={{ fontSize: '16px', marginBottom: '10px' }}>{result.end_death_rate_1} - {result.end_death_rate_2}</p>
          </div>
        </div>
      </div>
    );
}
