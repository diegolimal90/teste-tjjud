package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.ListarAssuntosIn;
import br.com.sdweb.agenda.boundary.out.AssuntoOut;
import br.com.sdweb.agenda.domain.Assunto;

import java.util.List;

public class ListarAssuntosUseCase implements ListarAssuntosIn {

    private final AssuntoOut assuntoOut;

    public ListarAssuntosUseCase(AssuntoOut assuntoOut) {
        this.assuntoOut = assuntoOut;
    }

    @Override
    public List<Assunto> listar() {
        return assuntoOut.listar();
    }
}
