package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.EditarLivroIn;
import br.com.sdweb.agenda.boundary.out.LivroOut;
import br.com.sdweb.agenda.domain.Livro;

public class EditarLivroUseCase implements EditarLivroIn {

    private final LivroOut livroOut;

    public EditarLivroUseCase(LivroOut livroOut) {
        this.livroOut = livroOut;
    }

    @Override
    public Livro editar(Livro livro) {
        return livroOut.atualizar(livro);
    }
}
