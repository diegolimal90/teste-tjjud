package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.SalvarLivroIn;
import br.com.sdweb.agenda.boundary.out.LivroOut;
import br.com.sdweb.agenda.domain.Livro;
import exceptions.BusinessException;
import exceptions.Errors;

import java.math.BigDecimal;

public class SalvarLivroUseCase implements SalvarLivroIn {

    private final LivroOut livroOut;

    public SalvarLivroUseCase(LivroOut livroOut) {
        this.livroOut = livroOut;
    }

    @Override
    public Livro salvar(Livro livro) {
        if (livro.getValor() != null && livro.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Valor monetario incorreto ou negativo", Errors.ERROR0001);
        }

        return livroOut.salvar(livro);
    }
}
