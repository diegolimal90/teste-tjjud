package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.BuscarAutorPorIdIn;
import br.com.sdweb.agenda.boundary.out.AutorOut;
import br.com.sdweb.agenda.domain.Autor;

public class BuscarAutorPorIdUseCase implements BuscarAutorPorIdIn {

    private final AutorOut autorOut;

    public BuscarAutorPorIdUseCase(AutorOut autorOut) {
        this.autorOut = autorOut;
    }

    @Override
    public Autor buscarPorId(String id) {
        return autorOut.buscarPorId(id);
    }
}
