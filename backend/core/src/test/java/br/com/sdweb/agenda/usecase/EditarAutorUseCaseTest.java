package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.domain.Autor;
import br.com.sdweb.agenda.boundary.in.EditarAutorIn;
import br.com.sdweb.agenda.boundary.out.AutorOut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EditarAutorUseCaseTest {

    @Mock
    private AutorOut autorOut;

    private EditarAutorIn editarAutorIn;

    @BeforeEach
    void setUp() {
        editarAutorIn = new EditarAutorUseCase(autorOut);
    }

    @Test
    @DisplayName("Dado que exista o autor 'George R. R. Martin', Quando altera o nome para 'George Raymond Richard Martin', Entao deve atualizar corretamente")
    void deveEditarAutorComSucesso() {
        String idAutor = "123e4567-e89b-12d3-a456-426614174000";
        Autor autorComNovosDados = new Autor(idAutor, "George Raymond Richard Martin");
        Autor autorAtualizado = new Autor(idAutor, "George R. R. Martin");

        when(autorOut.atualizar(any(Autor.class))).thenReturn(autorAtualizado);

        editarAutorIn.editar(autorComNovosDados);
        
        verify(autorOut, times(1)).atualizar(autorComNovosDados);
    }
}
