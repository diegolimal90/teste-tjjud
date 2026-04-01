export interface Autor {
  id: string;
  nome: string;
}

export interface Assunto {
  id: string;
  descricao: string;
}

export interface Livro {
  id?: string;
  titulo: string;
  editora: string;
  edicao: number;
  anoPublicacao: string;
  valor: number;
  autores?: Autor[];
  assuntos?: Assunto[];
}

export interface LivroRequest {
  titulo: string;
  editora: string;
  edicao: number;
  anoPublicacao: string;
  valor: number;
  autores: string[]; // Lista de IDs
  assuntos: string[]; // Lista de IDs
}
