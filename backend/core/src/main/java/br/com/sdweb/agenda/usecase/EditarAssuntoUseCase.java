package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.EditarAssuntoIn;
import br.com.sdweb.agenda.boundary.out.AssuntoOut;
import br.com.sdweb.agenda.domain.Assunto;

public class EditarAssuntoUseCase implements EditarAssuntoIn {

    private final AssuntoOut assuntoOut;

    public EditarAssuntoUseCase(AssuntoOut assuntoOut) {
        this.assuntoOut = assuntoOut;
    }

    @Override
    public Assunto editar(Assunto assunto) {
        return assuntoOut.atualizar(assunto);
    }
}
