import React from 'react';
import Modal from 'react-modal';
import BenchmarkService from '../../service/BenchmarkService';
import { useNavigate } from 'react-router-dom';

// Componente Modal para exclusão do Benchmark
export default function DeleteBenchmarkModal({ isOpen, closeDeleteModal, id }) {
  const navigateTo = useNavigate();

  function handleDelete() {
    BenchmarkService.deleteBenchmark(id)
      .then(() => {
        closeDeleteModal();
        navigateTo("/");
      })
      .catch(error => {
        console.error('Erro ao excluir o benchmark:', error);
      });
  }

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={closeDeleteModal}
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
      <h2 style={{ marginBottom: '20px', fontSize: '24px' }}>Confirmação de exclusão</h2>
      <p style={{ fontSize: '18px', marginBottom: '20px' }}>Tem certeza de que deseja excluir este benchmark?</p>
      <div style={{ display: 'flex', justifyContent: 'center' }}>
        <button
          onClick={handleDelete}
          style={{
            marginRight: '10px',
            padding: '10px 20px',
            borderRadius: '5px',
            background: '#dc3545',
            color: '#fff',
            border: 'none',
          }}
        >
          Excluir
        </button>
        <button
          onClick={closeDeleteModal}
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
}
