package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.GerarRelatorioIn;
import br.com.sdweb.agenda.boundary.out.RelatorioOut;
import br.com.sdweb.agenda.domain.RelatorioLivroAutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GerarRelatorioUseCaseTest {

    @Mock
    private RelatorioOut relatorioOut;

    private GerarRelatorioIn gerarRelatorioIn;

    @BeforeEach
    void setUp() {
        gerarRelatorioIn = new GerarRelatorioUseCase(relatorioOut);
    }

    @Test
    @DisplayName("Dado solicitacao de relatorio, Quando consumido a porta, Entao retorna lista completa")
    void deveGerarRelatorioComSucesso() {
        RelatorioLivroAutor mockRow = new RelatorioLivroAutor("1", "Clean Architecture", "Alta Books", 2, "2019", "Robert C. Martin", "Software");
        when(relatorioOut.listarRelatorio()).thenReturn(List.of(mockRow));

        List<RelatorioLivroAutor> response = gerarRelatorioIn.gerarRelatorio();

        assertEquals(1, response.size());
        assertEquals("Clean Architecture", response.get(0).getTitulo());
        assertEquals("Software", response.get(0).getAssuntoDescricao());
        verify(relatorioOut, times(1)).listarRelatorio();
    }
}
