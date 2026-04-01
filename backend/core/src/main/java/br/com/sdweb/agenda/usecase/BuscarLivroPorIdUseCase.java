package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.BuscarLivroPorIdIn;
import br.com.sdweb.agenda.boundary.out.LivroOut;
import br.com.sdweb.agenda.domain.Livro;

public class BuscarLivroPorIdUseCase implements BuscarLivroPorIdIn {

    private final LivroOut livroOut;

    public BuscarLivroPorIdUseCase(LivroOut livroOut) {
        this.livroOut = livroOut;
    }

    @Override
    public Livro buscarPorId(String id) {
        return livroOut.buscarPorId(id);
    }
}
