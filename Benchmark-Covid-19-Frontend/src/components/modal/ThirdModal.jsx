import React, { useState } from 'react';
import Modal from 'react-modal';
import { FaSpinner } from 'react-icons/fa'; // Importando o ícone de carregamento
import BenchmarkService from '../../service/BenchmarkService';
import { useNavigate } from 'react-router-dom';

const ThirdModal = ({ isOpen, onRequestClose , benchmark}) => {
  const navigateTo = useNavigate();
  const [isLoading, setIsLoading] = useState(false); // Estado para controlar o carregamento

  const handleCreateNewBenchmark = async () => {
    setIsLoading(true); // Ativa o carregamento enquanto a solicitação está em andamento

    try {
      const benchmarkCreate = await BenchmarkService.createBenchmark(benchmark);
      navigateTo(`/benchmark/${benchmarkCreate.id}`);
    } catch (error) {
      if (error.response.status === 429) alert('Por favor, tente novamente mais tarde!');
      if (error.response.status === 400) alert('Por favor, verifique os parâmetros!');
    }

    setIsLoading(false); // Desativa o carregamento após a conclusão da solicitação
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      style={{
        overlay: {
          backgroundColor: 'rgba(0, 0, 0, 0.5)',
          top: 0,
        },
        content: {
          top: '20%',
          left: '50%',
          right: 'auto',
          bottom: 'auto',
          marginRight: '-50%',
          transform: 'translate(-50%, 0)',
          borderRadius: '10px',
          boxShadow: '0 0 20px rgba(0, 0, 0, 0.3)',
          padding: '30px',
          maxWidth: '420px',
          background: '#fff',
          textAlign: 'center',
        },
      }}
    >
      <h2 style={{ marginBottom: '20px', fontSize: '24px' }}>Benchmark não encontrado!</h2>
      <p>Gostaria de criar um novo benchmark?</p>
      <div style={{ marginTop: '20px', display: 'flex', justifyContent: 'center' }}>
        <button
          onClick={handleCreateNewBenchmark}
          style={{
            marginRight: '10px',
            padding: '10px 20px',
            borderRadius: '5px',
            background: '#007bff',
            color: '#fff',
            border: 'none',
          }}
        >
          {isLoading ? <FaSpinner className="spinner" /> : 'Sim'} {/* Exibe o ícone de carregamento se isLoading for verdadeiro */}
        </button>
        <button
          onClick={onRequestClose}
          style={{
            padding: '10px 20px',
            borderRadius: '5px',
            background: '#6c757d',
            color: '#fff',
            border: 'none',
          }}
        >
          Não
        </button>
      </div>
    </Modal>
  );
};

export default ThirdModal;
