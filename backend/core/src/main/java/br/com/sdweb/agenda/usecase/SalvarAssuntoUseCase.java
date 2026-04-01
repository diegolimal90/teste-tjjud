package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.SalvarAssuntoIn;
import br.com.sdweb.agenda.boundary.out.AssuntoOut;
import br.com.sdweb.agenda.domain.Assunto;

public class SalvarAssuntoUseCase implements SalvarAssuntoIn {

    private final AssuntoOut assuntoOut;

    public SalvarAssuntoUseCase(AssuntoOut assuntoOut) {
        this.assuntoOut = assuntoOut;
    }

    @Override
    public Assunto salvar(Assunto assunto) {
        return assuntoOut.salvar(assunto);
    }
}
