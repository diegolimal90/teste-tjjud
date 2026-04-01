package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.domain.Autor;
import br.com.sdweb.agenda.boundary.in.SalvarAutorIn;
import br.com.sdweb.agenda.boundary.out.AutorOut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalvarAutorUseCaseTest {

    @Mock
    private AutorOut autorOut;

    private SalvarAutorIn salvarAutorIn; 

    @BeforeEach
    void setUp() {
        salvarAutorIn = new SalvarAutorUseCase(autorOut);
    }

    @Test
    @DisplayName("Dado que o usuario preenche o nome, Quando solicitar a inclusao, Entao o sistema deve persistir no banco e retornar o codigo")
    void deveSalvarAutorComSucesso() {
        Autor novoAutor = new Autor(null, "George R. R. Martin");
        Autor autorSalvo = new Autor("123e4567-e89b-12d3-a456-426614174000", "George R. R. Martin");

        when(autorOut.salvar(any(Autor.class))).thenReturn(autorSalvo);

        Autor resultado = salvarAutorIn.salvar(novoAutor);

        assertNotNull(resultado);
        assertEquals("123e4567-e89b-12d3-a456-426614174000", resultado.getId());
        assertEquals("George R. R. Martin", resultado.getNome());
        
        verify(autorOut, times(1)).salvar(novoAutor);
    }

}
