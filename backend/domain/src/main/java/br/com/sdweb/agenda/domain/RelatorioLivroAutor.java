package br.com.sdweb.agenda.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioLivroAutor {
    private String livroId;
    private String titulo;
    private String editora;
    private Integer edicao;
    private String anoPublicacao;
    private String autorNome;
    private String assuntoDescricao;
}
