import React from 'react';
import { NavLink } from 'react-router-dom';
import { Book, Users, Tag, FileText, Home, LogOut } from 'lucide-react';
import { useAuth } from '../context/AuthContext';

const Sidebar: React.FC = () => {
  const { logout } = useAuth();

  const navItems = [
    { name: 'Início', path: '/', icon: <Home size={20} /> },
    { name: 'Autores', path: '/autores', icon: <Users size={20} /> },
    { name: 'Assuntos', path: '/assuntos', icon: <Tag size={20} /> },
    { name: 'Livros', path: '/livros', icon: <Book size={20} /> },
    { name: 'Relatórios', path: '/relatorios', icon: <FileText size={20} /> },
  ];

  return (
    <div className="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark shadow" style={{ width: '280px', height: '100vh', position: 'fixed' }}>
      <a href="/" className="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
        <Book className="me-2" />
        <span className="fs-4 fw-bold text-primary">Gestão de Livros</span>
      </a>
      <hr />
      <ul className="nav nav-pills flex-column mb-auto">
        {navItems.map((item) => (
          <li key={item.name} className="nav-item">
            <NavLink
              to={item.path}
              className={({ isActive }) =>
                `nav-link d-flex align-items-center ${isActive ? 'active shadow' : 'text-white'}`
              }
            >
              <span className="me-3">{item.icon}</span>
              {item.name}
            </NavLink>
          </li>
        ))}
      </ul>
      <hr />
      <div className="dropdown">
        <button
          onClick={logout}
          className="btn btn-dark d-flex align-items-center text-white text-decoration-none w-100 border-0"
        >
          <LogOut className="me-3 text-danger" size={20} />
          <strong>Sair do Sistema</strong>
        </button>
      </div>
    </div>
  );
};

export default Sidebar;
