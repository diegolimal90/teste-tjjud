package br.com.sdweb.agenda.boundary.in;

import br.com.sdweb.agenda.domain.Livro;

public interface BuscarLivroPorIdIn {
    Livro buscarPorId(String id);
}
