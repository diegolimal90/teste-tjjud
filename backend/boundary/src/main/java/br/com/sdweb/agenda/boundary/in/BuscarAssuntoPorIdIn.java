package br.com.sdweb.agenda.boundary.in;

import br.com.sdweb.agenda.domain.Assunto;

public interface BuscarAssuntoPorIdIn {
    Assunto buscarPorId(String id);
}
