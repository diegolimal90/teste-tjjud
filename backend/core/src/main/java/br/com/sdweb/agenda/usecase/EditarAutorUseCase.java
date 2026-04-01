package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.EditarAutorIn;
import br.com.sdweb.agenda.boundary.out.AutorOut;
import br.com.sdweb.agenda.domain.Autor;

public class EditarAutorUseCase implements EditarAutorIn {

    private final AutorOut autorOut;

    public EditarAutorUseCase(AutorOut autorOut) {
        this.autorOut = autorOut;
    }

    @Override
    public void editar(Autor autor) {
        autorOut.atualizar(autor);
    }
}
