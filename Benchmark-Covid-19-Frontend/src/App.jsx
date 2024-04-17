import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import BenchmarkPage from './pages/BenchmarkPage';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/benchmark/:id" element={<BenchmarkPage />} /> 
      </Routes>
    </Router>
  );
}

export default App;
