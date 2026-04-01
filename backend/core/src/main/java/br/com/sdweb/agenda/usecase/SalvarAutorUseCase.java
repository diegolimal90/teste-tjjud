package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.SalvarAutorIn;
import br.com.sdweb.agenda.boundary.out.AutorOut;
import br.com.sdweb.agenda.domain.Autor;

public class SalvarAutorUseCase implements SalvarAutorIn {

    private final AutorOut autorOut;

    public SalvarAutorUseCase(AutorOut autorOut) {
        this.autorOut = autorOut;
    }

    @Override
    public Autor salvar(Autor autor) {
        return autorOut.salvar(autor);
    }
}
