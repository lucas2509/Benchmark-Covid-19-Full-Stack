
import React, { useState } from 'react';
import BenchmarkService from '../service/BenchmarkService';
import { useNavigate } from 'react-router-dom';

const ModalGetBenchmark = ({ closeModal, placeType, openBenchmarkNotFoundModal}) => {
  const [placeName1, setPlaceName1] = useState('');
  const [placeName2, setPlaceName2] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const navigateTo = useNavigate();

  const formatDate = (date) => {
    return date.toISOString().slice(0, 10);
  };

  const handleSubmit = async () => {
    if (!startDate || !endDate || !placeName1 || !placeName2){
      alert("Campos Vazios!");
      return;
    }
    var benchmark = {
      start_date: formatDate(new Date(startDate)),
      end_date: formatDate(new Date(endDate)),
      place_type: placeType,
      place_name_1: placeName1,
      place_name_2: placeName2
    };

    try {
      const responseId = await BenchmarkService.getBenchmarkIdbyParameters(benchmark);
      navigateTo(`/benchmark/${responseId}`);
    }catch (error) {
      if(error.response.status == 404) openBenchmarkNotFoundModal(benchmark);
    }

    closeModal();
  };

  return (
    <div>
      <h2>Novo Benchmark</h2>
      <h2>{placeType}</h2>
      <div>
        <label htmlFor="placeName1">Nome do lugar 1:</label>
        <input type="text" id="placeName1" value={placeName1} onChange={(e) => setPlaceName1(e.target.value)} />
      </div>
      <div>
        <label htmlFor="placeName2">Nome do lugar 2:</label>
        <input type="text" id="placeName2" value={placeName2} onChange={(e) => setPlaceName2(e.target.value)} />
      </div>
      <div>
        <label htmlFor="startDate">Data de início:</label>
        <input type="date" id="startDate" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
      </div>
      <div>
        <label htmlFor="endDate">Data de fim:</label>
        <input type="date" id="endDate" value={endDate} onChange={(e) => setEndDate(e.target.value)} />
      </div>
      <button onClick={handleSubmit}>Obter Benchmark</button>
    </div>
  );
};

export default ModalGetBenchmark;
