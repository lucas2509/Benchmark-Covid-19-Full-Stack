// HomePage.jsx
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import BenchmarkService from '../service/BenchmarkService';
import CustomModal from '../components/modal/CustomModal';

//HomePage que exibe os ultimos benchmark criados
export default function HomePage  () {
  const [benchmarks, setBenchmarks] = useState([]);
  const [isOpenCustomModal, setIsOpenCustomModal] = useState(false);

  useEffect(() => {
    const fetchBenchmarks = async () => {
      try {
        const data = await BenchmarkService.getAllBenchmark();
        setBenchmarks(data.slice(-5));
      } catch (error) {
        console.error('Erro ao buscar benchmarks:', error);
      }
    };

    fetchBenchmarks();
  }, []);

  function openModal ()  {
    setIsOpenCustomModal(true);
  };

  function closeModal () {
    setIsOpenCustomModal(false);
  };

  return (
    <div style={{ textAlign: 'center', background: '#f7f7f7', borderRadius: '10px', boxShadow: '0 0 40px rgba(0, 0, 0, 0.3)', padding: '20px', maxWidth: '800px', margin: '0 auto' }}>
      <img src="/imagem.png" alt="Imagem" style={{ display: 'block', margin: '0 auto' }} />

      <button onClick={openModal} style={{ marginTop: '20px', marginBottom: '20px', padding: '10px 20px', borderRadius: '5px', background: '#007bff', color: '#fff', border: 'none' }}>Obter benchmark</button>
      {benchmarks.length > 0 ? (
        <div style={{ display: 'grid', gap: '20px', justifyContent: 'center' }}>
          {benchmarks.map(benchmark => (
            <Link to={`/benchmark/${benchmark.id}`} style={{ textDecoration: 'none', color: '#333' }} key={benchmark.id}>
              <div style={{ background: '#fff', borderRadius: '10px', boxShadow: '0 0 20px rgba(0, 0, 0, 0.5)', padding: '20px', maxWidth: '400px' }}>
                <h3>Benchmark</h3>
                <p>{benchmark.place_name_1} X {benchmark.place_name_2}</p>
                <p>Período de {benchmark.start_date} a {benchmark.end_date}</p>
              </div>
            </Link>
          ))}
        </div>
      ) : (
        <p>Nenhum benchmark disponível.</p>
      )}
      <CustomModal isOpen={isOpenCustomModal} onRequestClose={closeModal}/>
      
      
    </div>
  );
};
