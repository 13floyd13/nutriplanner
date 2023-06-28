import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import UserPage from './pages/UserPage';
import FoodPage from './pages/FoodPage';
// Importez les autres pages ici

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/users" element={<UserPage />} />
        <Route path="/foods" element={<FoodPage />} />
        {/* Ajoutez plus de routes ici pour les autres pages */}
      </Routes>
    </Router>
  );
};

export default App;

