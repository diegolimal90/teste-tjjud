import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import LoginPage from '../pages/Login/LoginPage';
import Home from '../pages/Home/Home';
import AutoresPage from '../pages/Autores/AutoresPage';
import AssuntosPage from '../pages/Assuntos/AssuntosPage';
import LivrosPage from '../pages/Livros/LivrosPage';
import ReportsPage from '../pages/Relatorios/ReportsPage';

const AppRouter: React.FC = () => {
  const { isAuthenticated } = useAuth();

  return (
    <BrowserRouter>
      <Routes>
        <Route 
          path="/login" 
          element={!isAuthenticated ? <LoginPage /> : <Navigate to="/" />} 
        />
        
        {/* Rotas Protegidas */}
        <Route 
          path="/" 
          element={isAuthenticated ? <Home /> : <Navigate to="/login" />} 
        />
        <Route 
          path="/autores" 
          element={isAuthenticated ? <AutoresPage /> : <Navigate to="/login" />} 
        />
        <Route 
          path="/assuntos" 
          element={isAuthenticated ? <AssuntosPage /> : <Navigate to="/login" />} 
        />
        <Route 
          path="/livros" 
          element={isAuthenticated ? <LivrosPage /> : <Navigate to="/login" />} 
        />
        <Route 
          path="/relatorios" 
          element={isAuthenticated ? <ReportsPage /> : <Navigate to="/login" />} 
        />

        {/* Fallback */}
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  );
};

export default AppRouter;
