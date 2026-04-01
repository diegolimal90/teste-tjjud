package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.ExcluirAssuntoIn;
import br.com.sdweb.agenda.boundary.out.AssuntoOut;

public class ExcluirAssuntoUseCase implements ExcluirAssuntoIn {

    private final AssuntoOut assuntoOut;

    public ExcluirAssuntoUseCase(AssuntoOut assuntoOut) {
        this.assuntoOut = assuntoOut;
    }

    @Override
    public void excluir(String id) {
        assuntoOut.deletar(id);
    }
}
