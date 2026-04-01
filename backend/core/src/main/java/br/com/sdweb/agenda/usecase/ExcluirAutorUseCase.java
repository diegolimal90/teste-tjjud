package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.ExcluirAutorIn;
import br.com.sdweb.agenda.boundary.out.AutorOut;

public class ExcluirAutorUseCase implements ExcluirAutorIn {

    private final AutorOut autorOut;

    public ExcluirAutorUseCase(AutorOut autorOut) {
        this.autorOut = autorOut;
    }

    @Override
    public void excluir(String id) {
        autorOut.deletar(id);
    }
}
