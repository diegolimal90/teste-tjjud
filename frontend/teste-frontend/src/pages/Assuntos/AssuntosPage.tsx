import React, { useEffect, useState } from 'react';
import { dataService } from '../../services/dataService';
import type { Assunto } from '../../types';
import Layout from '../../components/Layout';
import { Plus, Edit, Trash2 } from 'lucide-react';

const AssuntosPage: React.FC = () => {
  const [assuntos, setAssuntos] = useState<Assunto[]>([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false);
  const [currentAssunto, setCurrentAssunto] = useState<Assunto | null>(null);
  const [descricao, setDescricao] = useState('');

  const fetchAssuntos = async () => {
    try {
      const response = await dataService.getAssuntos();
      setAssuntos(response.data);
    } catch (error) {
      console.error('Erro ao buscar assuntos', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchAssuntos();
  }, []);

  const handleSave = async () => {
    try {
      if (currentAssunto) {
        await dataService.editarAssunto(currentAssunto.id, descricao);
      } else {
        await dataService.salvarAssunto(descricao);
      }
      setShowModal(false);
      setDescricao('');
      setCurrentAssunto(null);
      fetchAssuntos();
    } catch (error) {
      alert('Erro ao salvar assunto');
    }
  };

  const handleEdit = (assunto: Assunto) => {
    setCurrentAssunto(assunto);
    setDescricao(assunto.descricao);
    setShowModal(true);
  };

  const handleDelete = async (id: string) => {
    if (window.confirm('Deseja realmente excluir este assunto?')) {
      try {
        await dataService.excluirAssunto(id);
        fetchAssuntos();
      } catch (error) {
        alert('Erro ao excluir assunto. Verifique se ele possui livros vinculados.');
      }
    }
  };

  return (
    <Layout>
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2>Assuntos</h2>
        <button className="btn btn-primary d-flex align-items-center" onClick={() => { setDescricao(''); setCurrentAssunto(null); setShowModal(true); }}>
          <Plus size={18} className="me-2" /> Novo Assunto
        </button>
      </div>

      <div className="card shadow-sm border-0 bg-dark-subtle">
        <div className="card-body p-0">
          <table className="table table-dark table-hover mb-0 align-middle">
            <thead>
              <tr>
                <th className="ps-3">ID</th>
                <th>Descrição</th>
                <th className="text-end pe-3">Ações</th>
              </tr>
            </thead>
            <tbody>
              {loading ? (
                <tr><td colSpan={3} className="text-center p-4">Carregando...</td></tr>
              ) : assuntos.length === 0 ? (
                <tr><td colSpan={3} className="text-center p-4">Nenhum assunto encontrado.</td></tr>
              ) : assuntos.map((assunto) => (
                <tr key={assunto.id}>
                  <td className="ps-3">{assunto.id.substring(0, 8)}...</td>
                  <td>{assunto.descricao}</td>
                  <td className="text-end pe-3">
                    <button className="btn btn-sm btn-outline-info me-2 rounded-circle" onClick={() => handleEdit(assunto)}>
                      <Edit size={16} />
                    </button>
                    <button className="btn btn-sm btn-outline-danger rounded-circle" onClick={() => handleDelete(assunto.id)}>
                      <Trash2 size={16} />
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      {showModal && (
        <div className="modal fade show d-block" style={{ backgroundColor: 'rgba(0,0,0,0.7)' }}>
          <div className="modal-dialog modal-dialog-centered">
            <div className="modal-content bg-dark border-secondary">
              <div className="modal-header border-secondary">
                <h5 className="modal-title font-monospace">{currentAssunto ? 'Editar Assunto' : 'Novo Assunto'}</h5>
                <button type="button" className="btn-close btn-close-white" onClick={() => setShowModal(false)}></button>
              </div>
              <div className="modal-body">
                <div className="mb-3">
                  <label className="form-label text-secondary small text-uppercase fw-bold">Descrição</label>
                  <input
                    type="text"
                    className="form-control bg-dark text-white border-secondary"
                    value={descricao}
                    onChange={(e) => setDescricao(e.target.value)}
                    placeholder="Ex: Programação, Matemática, Ficção..."
                  />
                </div>
              </div>
              <div className="modal-footer border-secondary">
                <button type="button" className="btn btn-outline-secondary" onClick={() => setShowModal(false)}>Fechar</button>
                <button type="button" className="btn btn-primary px-4" onClick={handleSave}>Salvar</button>
              </div>
            </div>
          </div>
        </div>
      )}
    </Layout>
  );
};

export default AssuntosPage;
