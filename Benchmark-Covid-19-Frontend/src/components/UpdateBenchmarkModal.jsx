
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import BenchmarkService from '../service/BenchmarkService'

const UpdateBenchmarkModal = ({ closeUpdateModal, benchmark}) => {
  const [placeName1, setPlaceName1] = useState('');
  const [placeName2, setPlaceName2] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const navigateTo = useNavigate();

  const cancelButtonClick = () => {
    closeUpdateModal();
  };

  const saveButtonClick = async () => {
    try {
      const benchmarkUpdated = {
        start_date: startDate,
        end_date: endDate,
        place_type: benchmark.place_type,
        place_name_1: placeName1,
        place_name_2: placeName2
      }

      console.log("benchmarkUpdated --> "+ JSON.stringify(benchmarkUpdated));
      console.log("benchmark ID--> "+ benchmark.id);

      await BenchmarkService.updateBenchmark(benchmark.id,benchmarkUpdated);
      closeUpdateModal();
      navigateTo(0);
    }catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    if (benchmark) {
      setPlaceName1(benchmark.place_name_1 || '');
      setPlaceName2(benchmark.place_name_2 || '');
      setStartDate(benchmark.start_date || '');
      setEndDate(benchmark.end_date || '');
    }
  }, [benchmark]);

  return (
    <div>
      <h3>Editar benchmark</h3>
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
      <button onClick={saveButtonClick}>Salvar</button>
      <button onClick={cancelButtonClick}>Cancelar</button>
    </div>
  );
};

export default UpdateBenchmarkModal;
