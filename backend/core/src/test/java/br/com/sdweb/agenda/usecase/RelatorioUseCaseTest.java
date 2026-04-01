package br.com.sdweb.agenda.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RelatorioUseCaseTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Quando pesquisado os dados para construir o Relatorio, Entao o DTO devolvido deve constar os 3 relacionamentos agrupados por Autor")
    void deveComprovarAgrupamentoDeInformacoesDasViews() {
        // O Relatorio busca na porta de saida referenciando dados advindos da view criada no DB
        // Arrange
        // Act
        // Assert
    }
}
