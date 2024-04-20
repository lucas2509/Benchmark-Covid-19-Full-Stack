import React, { useState } from 'react';
import Modal from 'react-modal';
import { useNavigate } from 'react-router-dom';
import BenchmarkService from '../../service/BenchmarkService';
import ThirdModal from './ThirdModal';

// Componente Modal para seleção dos parametros do Benchmark
export default function SecondModal({ isOpen, onRequestClose, selectedOption }) {
  const navigateTo = useNavigate();
  const [isThirdModalOpen, setIsThirdModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    startDate: '',
    endDate: '',
    placeName1: '',
    placeName2: '',
  });
  const [benchmark, setBenchmark] = useState(false);

  function handleCloseThirdModal() {
    setIsThirdModalOpen(false);
  }

  function handleChange(e) {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  }

  function closeThirdModal() {
    setIsThirdModalOpen(false);
  }

  async function handleButtonClick() {
    if (!formData.startDate || !formData.endDate || !formData.placeName1 || !formData.placeName2) {
        alert('Por favor, preencha todos os campos.');
        return; 
    }

    const benchmark = {
        start_date: formData.startDate,
        end_date: formData.endDate,
        place_type: selectedOption,
        place_name_1: formData.placeName1,
        place_name_2: formData.placeName2
    };
    setBenchmark(benchmark);
    

    try {
        const responseId = await BenchmarkService.getBenchmarkIdbyParameters(benchmark);
        navigateTo(`/benchmark/${responseId}`);
    }catch (error) {
        if(error.response.status == 404) setIsThirdModalOpen(true);
    }
  }

  return (
    <div>
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
        <h2 style={{ marginBottom: '20px', fontSize: '24px' }}>Preencha os campos abaixo:</h2>
        <div style={{ marginBottom: '20px' }}>
            <label htmlFor="placeName1">Nome do Primeiro Lugar:</label>
            <input type="text" id="placeName1" name="placeName1" value={formData.placeName1} onChange={handleChange} style={{ margin: '0 10px', padding: '8px', borderRadius: '5px', border: '1px solid #ccc' }} />
        </div>
        <div style={{ marginBottom: '20px' }}>
            <label htmlFor="placeName2">Nome do Segundo Lugar:</label>
            <input type="text" id="placeName2" name="placeName2" value={formData.placeName2} onChange={handleChange} style={{ margin: '0 10px', padding: '8px', borderRadius: '5px', border: '1px solid #ccc' }} />
        </div>
        <div style={{ marginBottom: '20px', display: 'flex', justifyContent: 'center' }}>
            <div style={{ marginRight: '20px' }}>
            <label htmlFor="startDate">Data Inicial:</label>
            <input type="date" id="startDate" name="startDate" value={formData.startDate} onChange={handleChange} style={{ margin: '0 10px', padding: '8px', borderRadius: '5px', border: '1px solid #ccc' }} />
            </div>
            <div>
            <label htmlFor="endDate">Data Final:</label>
            <input type="date" id="endDate" name="endDate" value={formData.endDate} onChange={handleChange} style={{ margin: '0 10px', padding: '8px', borderRadius: '5px', border: '1px solid #ccc' }} />
            </div>
        </div>
        <button onClick={handleButtonClick} style={{ padding: '10px 20px', borderRadius: '5px', background: '#007bff', color: '#fff', border: 'none' }}>Obter benchmark</button>
        </Modal>
        <ThirdModal isOpen={isThirdModalOpen} onRequestClose={handleCloseThirdModal} closeThirdModal={closeThirdModal}  benchmark={benchmark}/>
    </div>
  );
};