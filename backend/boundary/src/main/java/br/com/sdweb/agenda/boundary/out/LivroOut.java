package br.com.sdweb.agenda.boundary.out;

import br.com.sdweb.agenda.domain.Livro;

import java.util.List;

public interface LivroOut {
    Livro salvar(Livro livro);
    Livro atualizar(Livro livro);
    void deletar(String id);
    Livro buscarPorId(String id);
    List<Livro> listar();
}
