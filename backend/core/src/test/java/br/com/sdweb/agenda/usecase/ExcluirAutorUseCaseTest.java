package br.com.sdweb.agenda.usecase;

import br.com.sdweb.agenda.boundary.in.ExcluirAutorIn;
import br.com.sdweb.agenda.boundary.out.AutorOut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExcluirAutorUseCaseTest {

    @Mock
    private AutorOut autorOut;

    private ExcluirAutorIn excluirAutorIn;

    @BeforeEach
    void setUp() {
        excluirAutorIn = new ExcluirAutorUseCase(autorOut);
    }

    @Test
    @DisplayName("Dado que um autor nao possua vinculos (ex: chave estrangeira), Quando solicita exclusao, Entao deve apagar com sucesso")
    void deveExcluirAutorComSucesso() {
        String idAutor = "123e4567-e89b-12d3-a456-426614174000";

        // Caso a exclusão seja void, só invocamos e verificamos a chamada
        doNothing().when(autorOut).deletar(idAutor);

        excluirAutorIn.excluir(idAutor);

        verify(autorOut, times(1)).deletar(idAutor);
    }
}
