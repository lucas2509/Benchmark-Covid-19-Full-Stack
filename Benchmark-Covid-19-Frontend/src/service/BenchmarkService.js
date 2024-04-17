// BenchmarkService.js
import axios from 'axios';
import.meta.env.COVID_19_BACKEND_API_URL;

const API_URL = import.meta.env.VITE_COVID_19_BACKEND_API_URL;

const BenchmarkService = {
  getAllBenchmark: async () => {
    try {
      const response = await axios.get(API_URL);
      return response.data;
    } catch (error) {
      console.error('Erro ao obter benchmarks:', error);
      throw error;
    }
  },

  getBenchmarkById: async (id) => {
    try {
      const response = await axios.get(`${API_URL}/${id}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao obter benchmark por ID:', error);
      throw error;
    }
  },

  getBenchmarkIdbyParameters: async (params) => {
    try {
      const response = await axios.get(`${API_URL}/id`, {params});
      return response.data;
    } catch (error) {
      console.error('Erro ao obter a ID do benchmark', error);
      throw error;
    }
  },

  createBenchmark: async (benchmark) => {
    try {
      const response = await axios.post(API_URL, benchmark);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar benchmark:', error);
      throw error;
    }
  },

  updateBenchmark: async (id, updatedBenchmark) => {
    try {
      const response = await axios.put(`${API_URL}/${id}`, updatedBenchmark);
      return response.data;
    } catch (error) {
      console.error('Erro ao atualizar benchmark:', error);
      throw error;
    }
  },

  deleteBenchmark: async (id) => {
    try {
      return await axios.delete(`${API_URL}/${id}`);
    } catch (error) {
      console.error('Erro ao excluir benchmark:', error);
      throw error;
    }
  },

  getResultCityStateByBenchmarkId: async (id) => {
    try {
      const response = await axios.get(`${API_URL}/${id}/result/citystate`);
      return response.data;
    } catch (error) {
      console.error('Erro ao obter resultado city/state do benchmark:', error);
      throw error;
    }
  },

  getResultCountryByBenchmarkId: async (id) => {
    try {
      const response = await axios.get(`${API_URL}/${id}/result/country`);
      return response.data;
    } catch (error) {
      console.error('Erro ao obter resultado country do benchmark:', error);
      throw error;
    }
  },
};

export default BenchmarkService;