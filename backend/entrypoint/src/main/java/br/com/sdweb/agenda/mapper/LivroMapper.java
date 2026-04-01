package br.com.sdweb.agenda.mapper;

import br.com.sdweb.agenda.domain.Livro;
import br.com.sdweb.agenda.domain.Autor;
import br.com.sdweb.agenda.domain.Assunto;
import br.com.sdweb.agenda.controller.httmodel.request.LivroRequest;
import br.com.sdweb.agenda.controller.httmodel.response.LivroResponse;

import java.util.stream.Collectors;

public class LivroMapper {
    public static Livro toDomain(LivroRequest request) {
        return Livro.builder()
                .titulo(request.titulo())
                .editora(request.editora())
                .edicao(request.edicao())
                .anoPublicacao(request.anoPublicacao())
                .valor(request.valor())
                .autores(request.autores() != null ? request.autores().stream()
                        .map(id -> Autor.builder().id(id).build()).collect(Collectors.toList()) : null)
                .assuntos(request.assuntos() != null ? request.assuntos().stream()
                        .map(id -> Assunto.builder().id(id).build()).collect(Collectors.toList()) : null)
                .build();
    }

    public static LivroResponse toResponse(Livro domain) {
        return new LivroResponse(
                domain.getId(),
                domain.getTitulo(),
                domain.getEditora(),
                domain.getEdicao(),
                domain.getAnoPublicacao(),
                domain.getValor()
        );
    }
}
