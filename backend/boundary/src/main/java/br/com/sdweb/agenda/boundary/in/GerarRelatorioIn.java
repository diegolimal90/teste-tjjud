package br.com.sdweb.agenda.boundary.in;

import br.com.sdweb.agenda.domain.RelatorioLivroAutor;
import java.util.List;

public interface GerarRelatorioIn {
    List<RelatorioLivroAutor> gerarRelatorio();
}
