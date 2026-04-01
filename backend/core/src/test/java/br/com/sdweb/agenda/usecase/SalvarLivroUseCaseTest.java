package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.SalvarLivroIn;
import br.com.sdweb.agenda.boundary.out.AssuntoOut;
import br.com.sdweb.agenda.boundary.out.AutorOut;
import br.com.sdweb.agenda.boundary.out.LivroOut;
import br.com.sdweb.agenda.domain.Assunto;
import br.com.sdweb.agenda.domain.Autor;
import br.com.sdweb.agenda.domain.Livro;
import exceptions.BusinessException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalvarLivroUseCaseTest {

    @Mock
    private LivroOut livroOut;

    private SalvarLivroIn salvarLivroIn;

    @BeforeEach
    void setUp() {
        salvarLivroIn = new SalvarLivroUseCase(livroOut);
    }

    @Test
    @DisplayName("Dado um Livro com valor valido, Quando salvo, Entao armazena com sucesso")
    void deveSalvarLivroComValorValido() {
        Livro livro = new Livro(null, "Clean Code", "Prentice Hall", 1, "2008", new BigDecimal("150.00"), null, null);
        Livro persistido = new Livro("1", "Clean Code", "Prentice Hall", 1, "2008", new BigDecimal("150.00"), null, null);

        Assunto assunto = new Assunto("1", "Programacao");
        Autor autor = new Autor("1", "Programacao");

        List<Assunto> assuntos = new ArrayList<>();
        List<Autor> autores = new ArrayList<>();
        assuntos.add(assunto);
        autores.add(autor);
        livro.setAssuntos(assuntos);
        livro.setAutores(autores);
    
        when(livroOut.salvar(any(Livro.class))).thenReturn(persistido);

        Livro resultado = salvarLivroIn.salvar(livro);

        assertEquals("1", resultado.getId());
        assertEquals(new BigDecimal("150.00"), resultado.getValor());
        verify(livroOut, times(1)).salvar(livro);
    }

    @Test
    @DisplayName("Dado um Livro, Quando a camada de persistencia falhar por indisponibilidade, Entao o sistema retornara um erro")
    void dadoErroTimeout_QuandoSalva_EntaoLancaExceptionTratada() {
        Livro livro = new Livro(null, "Test Book", "Publisher", 1, "2024", new BigDecimal("50.00"), null, null);
        
        when(livroOut.salvar(any(Livro.class))).thenThrow(new BusinessException("Falha de comunicacao ao tentar persistir"));

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            salvarLivroIn.salvar(livro);
        });
        
        assertEquals("Falha de comunicacao ao tentar persistir", exception.getMessage());
        verify(livroOut, times(1)).salvar(livro);
    }
}
