package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.SalvarAssuntoIn;
import br.com.sdweb.agenda.boundary.out.AssuntoOut;
import br.com.sdweb.agenda.domain.Assunto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalvarAssuntoUseCaseTest {

    @Mock
    private AssuntoOut assuntoOut;

    private SalvarAssuntoIn salvarAssuntoIn;

    @BeforeEach
    void setUp() {
        salvarAssuntoIn = new SalvarAssuntoUseCase(assuntoOut);
    }

    @Test
    @DisplayName("Dado preenchimento de assunto como Ficcao Cientifica, Quando submetido, Entao armazena com integridade")
    void deveSalvarAssuntoComSucesso() {
        Assunto assuntoOriginal = new Assunto(null, "Ficcao Cientifica");
        Assunto assuntoPersistido = new Assunto("999e4567-e89b-12d3-a456-426614174999", "Ficcao Cientifica");

        when(assuntoOut.salvar(any(Assunto.class))).thenReturn(assuntoPersistido);

        Assunto resultado = salvarAssuntoIn.salvar(assuntoOriginal);

        assertNotNull(resultado);
        assertNotNull(resultado.getId());
        assertEquals("Ficcao Cientifica", resultado.getDescricao());

        verify(assuntoOut, times(1)).salvar(assuntoOriginal);
    }
}
