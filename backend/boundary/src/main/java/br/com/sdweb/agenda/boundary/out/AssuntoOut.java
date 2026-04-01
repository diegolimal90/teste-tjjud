package br.com.sdweb.agenda.boundary.out;

import br.com.sdweb.agenda.domain.Assunto;

import java.util.List;

public interface AssuntoOut {
    Assunto salvar(Assunto assunto);
    Assunto atualizar(Assunto assunto);
    void deletar(String id);
    Assunto buscarPorId(String id);
    List<Assunto> listar();
}
