import api from '../api/api';
import type { Autor, Assunto, Livro, LivroRequest } from '../types';

export const dataService = {
  // Autores
  getAutores: () => api.get<Autor[]>('/autores'),
  salvarAutor: (nome: string) => api.post('/autores', { nome }),
  editarAutor: (id: string, nome: string) => api.put(`/autores/${id}`, { nome }),
  excluirAutor: (id: string) => api.delete(`/autores/${id}`),

  // Assuntos
  getAssuntos: () => api.get<Assunto[]>('/assuntos'),
  salvarAssunto: (descricao: string) => api.post('/assuntos', { descricao }),
  editarAssunto: (id: string, descricao: string) => api.put(`/assuntos/${id}`, { descricao }),
  excluirAssunto: (id: string) => api.delete(`/assuntos/${id}`),

  // Livros
  getLivros: () => api.get<Livro[]>('/livros'),
  salvarLivro: (livro: LivroRequest) => api.post('/livros', livro),
  editarLivro: (id: string, livro: LivroRequest) => api.put(`/livros/${id}`, livro),
  excluirLivro: (id: string) => api.delete(`/livros/${id}`),

  // Relatórios
  gerarRelatorioPdf: () => api.get('/relatorios', { responseType: 'blob' }),
};
