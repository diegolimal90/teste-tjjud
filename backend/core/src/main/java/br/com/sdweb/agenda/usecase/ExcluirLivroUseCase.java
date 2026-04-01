package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.ExcluirLivroIn;
import br.com.sdweb.agenda.boundary.out.LivroOut;

public class ExcluirLivroUseCase implements ExcluirLivroIn {

    private final LivroOut livroOut;

    public ExcluirLivroUseCase(LivroOut livroOut) {
        this.livroOut = livroOut;
    }

    @Override
    public void excluir(String id) {
        livroOut.deletar(id);
    }
}
