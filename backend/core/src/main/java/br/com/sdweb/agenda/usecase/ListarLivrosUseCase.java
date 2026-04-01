package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.ListarLivrosIn;
import br.com.sdweb.agenda.boundary.out.LivroOut;
import br.com.sdweb.agenda.domain.Livro;

import java.util.List;

public class ListarLivrosUseCase implements ListarLivrosIn {

    private final LivroOut livroOut;

    public ListarLivrosUseCase(LivroOut livroOut) {
        this.livroOut = livroOut;
    }

    @Override
    public List<Livro> listar() {
        return livroOut.listar();
    }
}
