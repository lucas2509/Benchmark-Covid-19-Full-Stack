import React, { useState } from 'react';
import Modal from 'react-modal';
import SecondModal from './SecondModal';

// Componente Modal para seleção do tipo de Benchmark
export default function CustomModal ({ isOpen, onRequestClose }) {
  const [selectedOption, setSelectedOption] = useState(null);
  const [isSecondModalOpen, setIsSecondModalOpen] = useState(false);

  function handleOptionClick (option) {
    setSelectedOption(option);
    
    setIsSecondModalOpen(true);
  };

  function handleCloseSecondModal () {
    setIsSecondModalOpen(false);
  };

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
          <h2 style={{ marginBottom: '20px', fontSize: '24px' }}>Escolha uma opção:</h2>
          <div style={{ display: 'flex', justifyContent: 'center' }}>
            <button
              onClick={function() { handleOptionClick('country'); }}
              style={{ marginRight: '10px', padding: '10px 20px', borderRadius: '5px', background: '#6c757d', color: '#fff', border: 'none' }}
            >
              País
            </button>
            <button
              onClick={function() { handleOptionClick('state'); }}
              style={{ marginRight: '10px', padding: '10px 20px', borderRadius: '5px', background: '#6c757d', color: '#fff', border: 'none' }}
            >
              Estado
            </button>
            <button
              onClick={function() { handleOptionClick('city'); }}
              style={{ padding: '10px 20px', borderRadius: '5px', background: '#6c757d', color: '#fff', border: 'none' }}
            >
              Cidade
            </button>
          </div>
          <SecondModal isOpen={isSecondModalOpen} onRequestClose={handleCloseSecondModal} selectedOption={selectedOption} />
        </Modal>
    </div>
  );
};