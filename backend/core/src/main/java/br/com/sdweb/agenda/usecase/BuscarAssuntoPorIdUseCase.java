package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.BuscarAssuntoPorIdIn;
import br.com.sdweb.agenda.boundary.out.AssuntoOut;
import br.com.sdweb.agenda.domain.Assunto;

public class BuscarAssuntoPorIdUseCase implements BuscarAssuntoPorIdIn {

    private final AssuntoOut assuntoOut;

    public BuscarAssuntoPorIdUseCase(AssuntoOut assuntoOut) {
        this.assuntoOut = assuntoOut;
    }

    @Override
    public Assunto buscarPorId(String id) {
        return assuntoOut.buscarPorId(id);
    }
}
