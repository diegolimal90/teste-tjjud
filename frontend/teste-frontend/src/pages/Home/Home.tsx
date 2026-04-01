import React from 'react';
import { useAuth } from '../../context/AuthContext';
import Layout from '../../components/Layout';

const Home: React.FC = () => {
  const { logout } = useAuth();

  return (
    <Layout>
      <div className="d-flex flex-column justify-content-center align-items-center h-100 mt-5">
        <div className="text-center p-5 bg-dark rounded-4 shadow-sm border border-secondary">
          <h1 className="mb-4 display-4">Olá! 👋</h1>
          <p className="lead mb-4 text-secondary">Você está devidamente autenticado no sistema de Gestão de Livros.</p>
          <button onClick={logout} className="btn btn-outline-danger px-4">
            Encerrar Sessão
          </button>
        </div>
      </div>
    </Layout>
  );
};

export default Home;
