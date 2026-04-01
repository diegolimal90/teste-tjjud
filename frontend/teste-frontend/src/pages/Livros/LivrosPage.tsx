import React, { useEffect, useState } from 'react';
import { dataService } from '../../services/dataService';
import type { Livro, Autor, Assunto, LivroRequest } from '../../types';
import Layout from '../../components/Layout';
import { Plus, Edit, Trash2, BookOpen } from 'lucide-react';

const LivrosPage: React.FC = () => {
  const [livros, setLivros] = useState<Livro[]>([]);
  const [autores, setAutores] = useState<Autor[]>([]);
  const [assuntos, setAssuntos] = useState<Assunto[]>([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false);
  const [currentId, setCurrentId] = useState<string | null>(null);

  // Form State
  const [titulo, setTitulo] = useState('');
  const [editora, setEditora] = useState('');
  const [edicao, setEdicao] = useState<number>(1);
  const [anoPublicacao, setAnoPublicacao] = useState('');
  const [valor, setValor] = useState<number>(0);
  const [selectedAutores, setSelectedAutores] = useState<string[]>([]);
  const [selectedAssuntos, setSelectedAssuntos] = useState<string[]>([]);

  const fetchData = async () => {
    try {
      const [resLivros, resAutores, resAssuntos] = await Promise.all([
        dataService.getLivros(),
        dataService.getAutores(),
        dataService.getAssuntos()
      ]);
      setLivros(resLivros.data);
      setAutores(resAutores.data);
      setAssuntos(resAssuntos.data);
    } catch (error) {
      console.error('Erro ao buscar dados', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleSave = async () => {
    const request: LivroRequest = {
      titulo,
      editora,
      edicao,
      anoPublicacao,
      valor,
      autores: selectedAutores,
      assuntos: selectedAssuntos
    };

    try {
      if (currentId) {
        await dataService.editarLivro(currentId, request);
      } else {
        await dataService.salvarLivro(request);
      }
      setShowModal(false);
      resetForm();
      fetchData();
    } catch (error) {
      alert('Erro ao salvar livro. Verifique os campos.');
    }
  };

  const resetForm = () => {
    setTitulo('');
    setEditora('');
    setEdicao(1);
    setAnoPublicacao('');
    setValor(0);
    setSelectedAutores([]);
    setSelectedAssuntos([]);
    setCurrentId(null);
  };

  const handleEdit = (livro: Livro) => {
    setCurrentId(livro.id || null);
    setTitulo(livro.titulo);
    setEditora(livro.editora);
    setEdicao(livro.edicao);
    setAnoPublicacao(livro.anoPublicacao);
    setValor(livro.valor);
    setSelectedAutores(livro.autores?.map(a => a.id) || []);
    setSelectedAssuntos(livro.assuntos?.map(a => a.id) || []);
    setShowModal(true);
  };

  const handleDelete = async (id: string | undefined) => {
    if (!id) return;
    if (window.confirm('Excluir este livro permanentemente?')) {
      try {
        await dataService.excluirLivro(id);
        fetchData();
      } catch (error) {
        alert('Erro ao excluir livro');
      }
    }
  };

  const toggleAutor = (id: string) => {
    setSelectedAutores(prev => 
      prev.includes(id) ? prev.filter(a => a !== id) : [...prev, id]
    );
  };

  const toggleAssunto = (id: string) => {
    setSelectedAssuntos(prev => 
      prev.includes(id) ? prev.filter(a => a !== id) : [...prev, id]
    );
  };

  return (
    <Layout>
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2><BookOpen className="me-2 text-primary" /> Livros</h2>
        <button className="btn btn-primary px-4 d-flex align-items-center" onClick={() => { resetForm(); setShowModal(true); }}>
          <Plus size={20} className="me-2" /> Adicionar Livro
        </button>
      </div>

      <div className="card shadow-sm border-0 bg-dark-subtle overflow-hidden">
        <div className="table-responsive">
          <table className="table table-dark table-hover mb-0 align-middle">
            <thead>
              <tr className="border-bottom border-secondary">
                <th className="ps-4">Título</th>
                <th>Editora</th>
                <th>Edição</th>
                <th>Ano</th>
                <th>Valor</th>
                <th className="text-end pe-4">Ações</th>
              </tr>
            </thead>
            <tbody>
              {loading ? (
                <tr><td colSpan={6} className="text-center p-5">Buscando acervo...</td></tr>
              ) : livros.length === 0 ? (
                <tr><td colSpan={6} className="text-center p-5 text-muted">Nenhum livro cadastrado.</td></tr>
              ) : livros.map((livro) => (
                <tr key={livro.id}>
                  <td className="ps-4"><span className="fw-bold">{livro.titulo}</span></td>
                  <td><span className="badge bg-secondary opacity-75">{livro.editora}</span></td>
                  <td>{livro.edicao}ª</td>
                  <td>{livro.anoPublicacao}</td>
                  <td>{new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(livro.valor)}</td>
                  <td className="text-end pe-4">
                    <button className="btn btn-sm btn-icon btn-outline-warning me-2" onClick={() => handleEdit(livro)}>
                      <Edit size={16} />
                    </button>
                    <button className="btn btn-sm btn-icon btn-outline-danger" onClick={() => handleDelete(livro.id)}>
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
        <div className="modal fade show d-block" style={{ backgroundColor: 'rgba(0,0,0,0.85)' }}>
          <div className="modal-dialog modal-lg modal-dialog-centered">
            <div className="modal-content bg-dark border-secondary">
              <div className="modal-header border-secondary py-3">
                <h5 className="modal-title font-monospace text-primary">{currentId ? 'EDITAR LIVRO' : 'CADASTRAR NOVO LIVRO'}</h5>
                <button type="button" className="btn-close btn-close-white" onClick={() => setShowModal(false)}></button>
              </div>
              <div className="modal-body p-4" style={{ maxHeight: '70vh', overflowY: 'auto' }}>
                <div className="row g-3">
                  <div className="col-md-8">
                    <label className="form-label text-secondary small text-uppercase">Título</label>
                    <input type="text" className="form-control bg-dark text-white border-secondary" value={titulo} onChange={e => setTitulo(e.target.value)} />
                  </div>
                  <div className="col-md-4">
                    <label className="form-label text-secondary small text-uppercase">Ano</label>
                    <input type="text" className="form-control bg-dark text-white border-secondary" value={anoPublicacao} onChange={e => setAnoPublicacao(e.target.value)} maxLength={4} />
                  </div>
                  <div className="col-md-5">
                    <label className="form-label text-secondary small text-uppercase">Editora</label>
                    <input type="text" className="form-control bg-dark text-white border-secondary" value={editora} onChange={e => setEditora(e.target.value)} />
                  </div>
                  <div className="col-md-3">
                    <label className="form-label text-secondary small text-uppercase">Edição</label>
                    <input type="number" className="form-control bg-dark text-white border-secondary" value={edicao} onChange={e => setEdicao(Number(e.target.value))} min={1} />
                  </div>
                  <div className="col-md-4">
                    <label className="form-label text-secondary small text-uppercase">Valor (R$)</label>
                    <input type="number" className="form-control bg-dark text-white border-secondary" value={valor} onChange={e => setValor(Number(e.target.value))} step="0.01" />
                  </div>

                  <div className="col-md-6 mt-4">
                    <h6 className="border-bottom border-secondary pb-2 mb-3 text-info">AUTORES</h6>
                    <div className="bg-secondary bg-opacity-10 rounded p-3" style={{ height: '180px', overflowY: 'auto' }}>
                      {autores.map(autor => (
                        <div className="form-check mb-2" key={autor.id}>
                          <input 
                            className="form-check-input" 
                            type="checkbox" 
                            checked={selectedAutores.includes(autor.id)}
                            onChange={() => toggleAutor(autor.id)}
                          />
                          <label className="form-check-label small">{autor.nome}</label>
                        </div>
                      ))}
                    </div>
                  </div>

                  <div className="col-md-6 mt-4">
                    <h6 className="border-bottom border-secondary pb-2 mb-3 text-info">ASSUNTOS</h6>
                    <div className="bg-secondary bg-opacity-10 rounded p-3" style={{ height: '180px', overflowY: 'auto' }}>
                      {assuntos.map(assunto => (
                        <div className="form-check mb-2" key={assunto.id}>
                          <input 
                            className="form-check-input" 
                            type="checkbox" 
                            checked={selectedAssuntos.includes(assunto.id)}
                            onChange={() => toggleAssunto(assunto.id)}
                          />
                          <label className="form-check-label small">{assunto.descricao}</label>
                        </div>
                      ))}
                    </div>
                  </div>
                </div>
              </div>
              <div className="modal-footer border-secondary p-3">
                <button type="button" className="btn btn-outline-secondary px-4" onClick={() => setShowModal(false)}>CANCELAR</button>
                <button type="button" className="btn btn-primary px-5 fw-bold" onClick={handleSave}>SALVAR LIVRO</button>
              </div>
            </div>
          </div>
        </div>
      )}
    </Layout>
  );
};

export default LivrosPage;
