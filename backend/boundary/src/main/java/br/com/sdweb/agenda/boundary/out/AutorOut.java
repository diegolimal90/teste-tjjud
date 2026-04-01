package br.com.sdweb.agenda.boundary.out;

import br.com.sdweb.agenda.domain.Autor;

import java.util.List;

public interface AutorOut {
    Autor salvar(Autor autor);
    Autor atualizar(Autor autor);
    void deletar(String id);
    Autor buscarPorId(String id);
    List<Autor> listar();
}
