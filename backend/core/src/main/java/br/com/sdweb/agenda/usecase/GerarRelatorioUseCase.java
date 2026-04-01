package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.GerarRelatorioIn;
import br.com.sdweb.agenda.boundary.out.RelatorioOut;
import br.com.sdweb.agenda.domain.RelatorioLivroAutor;

import java.util.List;

public class GerarRelatorioUseCase implements GerarRelatorioIn {

    private final RelatorioOut relatorioOut;

    public GerarRelatorioUseCase(RelatorioOut relatorioOut) {
        this.relatorioOut = relatorioOut;
    }

    @Override
    public List<RelatorioLivroAutor> gerarRelatorio() {
        return relatorioOut.listarRelatorio();
    }
}
