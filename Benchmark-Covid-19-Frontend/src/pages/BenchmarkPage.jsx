import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import BenchmarkService from '../service/BenchmarkService';
import ResultCountryComponent from '../components/ResultCountryComponent';
import ResultCityStateComponent from '../components/ResultCityStateComponent';
import DeleteBenchmarkModal from '../components/modal/DeleteBenchmarkModal';
import UpdateBenchmarkModal from '../components/modal/UpdateBenchmarkModal';

const BenchmarkPage = () => {
  const { id } = useParams(); 
  const [benchmark, setBenchmark] = useState(null);
  const [isResultCountryComponentOpen, setIsResultCountryComponentOpen] = useState(false);
  const [isResultCityStateComponentOpen, setIsResultCityStateComponentOpen] = useState(false);
  const [isResultDeleteBenchmarkModalOpen, setIsDeleteBenchmarkModalOpen] = useState(false);
  const [isUpdateBenchmarkModalOpen, setIsUpdateBenchmarkModalOpen] = useState(false);


  const handleEdit = () => {
    setIsUpdateBenchmarkModalOpen(true);
  };

  const handleDelete = () => {
    setIsDeleteBenchmarkModalOpen(true);
  };

  const closeDeleteBenchmarkModal = () => {
    setIsDeleteBenchmarkModalOpen(false);
  };

  const closeUpdateBenchmarkModal = () => {
    setIsUpdateBenchmarkModalOpen(false);
  };


  useEffect(() => {
    const fetchBenchmark = async () => {
      try {
        const benchmarkData = await BenchmarkService.getBenchmarkById(id);
        setBenchmark(benchmarkData);
      } catch (error) {
        console.error('Erro ao buscar o benchmark:', error);
      }
    };

    fetchBenchmark();
  }, [id]);

  useEffect(() => {
    if (benchmark) {
      if (benchmark.place_type === "country") setIsResultCountryComponentOpen(true);
      if (benchmark.place_type === "city" || benchmark.place_type === "state") setIsResultCityStateComponentOpen(true);
    }
  }, [benchmark]);

  return (
    <div style={{ textAlign: 'center', padding: '20px', maxWidth: '800px', margin: '0 auto' }}>
      {benchmark ? (
        <div style={{ marginBottom: '20px', background: '#fff', borderRadius: '10px', boxShadow: '0 0 20px rgba(0, 0, 0, 0.5)', padding: '20px' }}>
          <h1 style={{ fontSize: '24px', marginBottom: '10px' }}>Benchmark</h1>
          <p style={{ fontSize: '18px', marginBottom: '10px' }}>Data de Início: {benchmark.start_date}</p>
          <p style={{ fontSize: '18px', marginBottom: '10px' }}>Data de Fim: {benchmark.end_date}</p>
          <p style={{ fontSize: '18px', marginBottom: '10px' }}>Tipo de Lugar: {benchmark.place_type}</p>
          <p style={{ fontSize: '18px', marginBottom: '10px' }}>Localização 1: {benchmark.place_name_1}</p>
          <p style={{ fontSize: '18px', marginBottom: '10px' }}>Localização 2: {benchmark.place_name_2}</p>
          <button onClick={handleEdit} style={{ marginRight: '10px', padding: '10px 20px', borderRadius: '5px', background: '#007bff', color: '#fff', border: 'none' }}>Editar</button>
          <button onClick={handleDelete} style={{ padding: '10px 20px', borderRadius: '5px', background: '#dc3545', color: '#fff', border: 'none' }}>Deletar</button>
        </div>
      ) : (
        <p style={{ fontSize: '18px' }}>Carregando dados do benchmark...</p>
      )}
      <DeleteBenchmarkModal isOpen={isResultDeleteBenchmarkModalOpen} closeDeleteModal={closeDeleteBenchmarkModal} id={id}/>
      <UpdateBenchmarkModal isOpen={isUpdateBenchmarkModalOpen} closeUpdateModal={closeUpdateBenchmarkModal} benchmark={benchmark}/>
      { isResultCountryComponentOpen && <ResultCountryComponent benchmark={benchmark}/> }
      { isResultCityStateComponentOpen && <ResultCityStateComponent benchmark={benchmark}/> }
    </div>
  );
};

export default BenchmarkPage;
