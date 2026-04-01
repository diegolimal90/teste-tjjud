package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.ListarAutoresIn;
import br.com.sdweb.agenda.boundary.out.AutorOut;
import br.com.sdweb.agenda.domain.Autor;

import java.util.List;

public class ListarAutoresUseCase implements ListarAutoresIn {

    private final AutorOut autorOut;

    public ListarAutoresUseCase(AutorOut autorOut) {
        this.autorOut = autorOut;
    }

    @Override
    public List<Autor> listar() {
        return autorOut.listar();
    }
}
