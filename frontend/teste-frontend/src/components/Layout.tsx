import React from 'react';
import Sidebar from '../components/Sidebar';
import type { ReactNode } from 'react';

interface LayoutProps {
  children: ReactNode;
}

const Layout: React.FC<LayoutProps> = ({ children }) => {
  return (
    <div className="d-flex">
      <Sidebar />
      <main className="flex-grow-1" style={{ marginLeft: '280px', padding: '2rem', minHeight: '100vh', backgroundColor: '#121212' }}>
        <div className="container-fluid">
          {children}
        </div>
      </main>
    </div>
  );
};

export default Layout;
