package br.com.sdweb.agenda.boundary.in;

import br.com.sdweb.agenda.domain.Autor;

public interface BuscarAutorPorIdIn {
    Autor buscarPorId(String id);
}
