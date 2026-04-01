import React, { useEffect, useState } from 'react';
import { dataService } from '../../services/dataService';
import type { Autor } from '../../types';
import Layout from '../../components/Layout';
import { Plus, Edit, Trash2 } from 'lucide-react';

const AutoresPage: React.FC = () => {
  const [autores, setAutores] = useState<Autor[]>([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false);
  const [currentAutor, setCurrentAutor] = useState<Autor | null>(null);
  const [nome, setNome] = useState('');

  const fetchAutores = async () => {
    try {
      const response = await dataService.getAutores();
      setAutores(response.data);
    } catch (error) {
      console.error('Erro ao buscar autores', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchAutores();
  }, []);

  const handleSave = async () => {
    try {
      if (currentAutor) {
        await dataService.editarAutor(currentAutor.id, nome);
      } else {
        await dataService.salvarAutor(nome);
      }
      setShowModal(false);
      setNome('');
      setCurrentAutor(null);
      fetchAutores();
    } catch (error) {
      alert('Erro ao salvar autor');
    }
  };

  const handleEdit = (autor: Autor) => {
    setCurrentAutor(autor);
    setNome(autor.nome);
    setShowModal(true);
  };

  const handleDelete = async (id: string) => {
    if (window.confirm('Deseja realmente excluir este autor?')) {
      try {
        await dataService.excluirAutor(id);
        fetchAutores();
      } catch (error) {
        alert('Erro ao excluir autor. Verifique se ele possui livros vinculados.');
      }
    }
  };

  return (
    <Layout>
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2>Autores</h2>
        <button className="btn btn-primary d-flex align-items-center" onClick={() => { setNome(''); setCurrentAutor(null); setShowModal(true); }}>
          <Plus size={18} className="me-2" /> Novo Autor
        </button>
      </div>

      <div className="card shadow-sm">
        <div className="card-body p-0">
          <table className="table table-dark table-hover mb-0">
            <thead>
              <tr>
                <th>ID</th>
                <th>Nome</th>
                <th className="text-end">Ações</th>
              </tr>
            </thead>
            <tbody>
              {loading ? (
                <tr><td colSpan={3} className="text-center p-4">Carregando...</td></tr>
              ) : autores.length === 0 ? (
                <tr><td colSpan={3} className="text-center p-4">Nenhum autor encontrado.</td></tr>
              ) : autores.map((autor) => (
                <tr key={autor.id}>
                  <td>{autor.id.substring(0, 8)}...</td>
                  <td>{autor.nome}</td>
                  <td className="text-end">
                    <button className="btn btn-sm btn-outline-info me-2" onClick={() => handleEdit(autor)}>
                      <Edit size={16} />
                    </button>
                    <button className="btn btn-sm btn-outline-danger" onClick={() => handleDelete(autor.id)}>
                      <Trash2 size={16} />
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      {/* Modal Simples (via condicional React por ser Vanilla Bootstrap) */}
      {showModal && (
        <div className="modal fade show d-block" style={{ backgroundColor: 'rgba(0,0,0,0.5)' }}>
          <div className="modal-dialog modal-dialog-centered">
            <div className="modal-content bg-dark border-secondary">
              <div className="modal-header border-secondary">
                <h5 className="modal-title">{currentAutor ? 'Editar Autor' : 'Novo Autor'}</h5>
                <button type="button" className="btn-close btn-close-white" onClick={() => setShowModal(false)}></button>
              </div>
              <div className="modal-body">
                <div className="mb-3">
                  <label className="form-label">Nome do Autor</label>
                  <input
                    type="text"
                    className="form-control bg-dark text-white border-secondary"
                    value={nome}
                    onChange={(e) => setNome(e.target.value)}
                    placeholder="Digite o nome completo"
                  />
                </div>
              </div>
              <div className="modal-footer border-secondary">
                <button type="button" className="btn btn-secondary" onClick={() => setShowModal(false)}>Cancelar</button>
                <button type="button" className="btn btn-primary" onClick={handleSave}>Salvar</button>
              </div>
            </div>
          </div>
        </div>
      )}
    </Layout>
  );
};

export default AutoresPage;
