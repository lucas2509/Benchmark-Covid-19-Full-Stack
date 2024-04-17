import React, { useState, useEffect } from 'react';
import Modal from 'react-modal';
import BenchmarkService from '../../service/BenchmarkService';
import { useNavigate } from 'react-router-dom';
import { FaSpinner } from 'react-icons/fa'; // Importando o ícone de carregamento

const UpdateBenchmarkModal = ({ isOpen, closeUpdateModal, benchmark }) => {
  const navigateTo = useNavigate();
  const [isLoading, setIsLoading] = useState(false); // Estado para controlar o carregamento

  const [formData, setFormData] = useState({
    start_date: '',
    end_date: '',
    place_type: '',
    place_name_1: '',
    place_name_2: ''
  });

  useEffect(() => {
    if (benchmark) {
      setFormData({
        start_date: benchmark.start_date,
        end_date: benchmark.end_date,
        place_type: benchmark.place_type,
        place_name_1: benchmark.place_name_1,
        place_name_2: benchmark.place_name_2
      });
    }
  }, [benchmark]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = async () => {
    try {
      setIsLoading(true);
      await BenchmarkService.updateBenchmark(benchmark.id, formData);
      setIsLoading(false);
      closeUpdateModal();
      navigateTo(0);
    } catch (error) {
      console.error('Erro ao atualizar o benchmark:', error);
    }
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={closeUpdateModal}
      style={{
        overlay: {
          backgroundColor: 'rgba(0, 0, 0, 0.5)',
          top: 0,
        },
        content: {
          top: '50%',
          left: '50%',
          right: 'auto',
          bottom: 'auto',
          marginRight: '-50%',
          transform: 'translate(-50%, -50%)',
          borderRadius: '10px',
          boxShadow: '0 0 20px rgba(0, 0, 0, 0.3)',
          padding: '30px',
          maxWidth: '400px',
          background: '#fff',
          textAlign: 'center',
        },
      }}
    >
      <h2 style={{ marginBottom: '20px', fontSize: '24px' }}>Atualizar Benchmark</h2>
      <input
        type="date"
        name="start_date"
        value={formData.start_date}
        onChange={handleChange}
        style={{ marginBottom: '10px' }}
      />
      <input
        type="date"
        name="end_date"
        value={formData.end_date}
        onChange={handleChange}
        style={{ marginBottom: '10px' }}
      />
      <input
        type="text"
        name="place_type"
        value={formData.place_type}
        onChange={handleChange}
        placeholder="Tipo de Lugar"
        style={{ marginBottom: '10px' }}
      />
      <input
        type="text"
        name="place_name_1"
        value={formData.place_name_1}
        onChange={handleChange}
        placeholder="Localização 1"
        style={{ marginBottom: '10px' }}
      />
      <input
        type="text"
        name="place_name_2"
        value={formData.place_name_2}
        onChange={handleChange}
        placeholder="Localização 2"
        style={{ marginBottom: '20px' }}
      />
      <div style={{ display: 'flex', justifyContent: 'center' }}>
        <button
          onClick={handleSubmit}
          style={{
            marginRight: '10px',
            padding: '10px 20px',
            borderRadius: '5px',
            background: '#007bff',
            color: '#fff',
            border: 'none',
          }}
        >{isLoading ? <FaSpinner className="spinner" /> : 'Atualizar'} {/* Exibe o ícone de carregamento se isLoading for verdadeiro */}
        </button>
        <button
          onClick={closeUpdateModal}
          style={{
            padding: '10px 20px',
            borderRadius: '5px',
            background: '#6c757d',
            color: '#fff',
            border: 'none',
          }}
        >
          Cancelar
        </button>
      </div>
    </Modal>
  );
};

export default UpdateBenchmarkModal;
