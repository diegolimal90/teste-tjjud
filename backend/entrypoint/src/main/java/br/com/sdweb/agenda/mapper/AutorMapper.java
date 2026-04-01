package br.com.sdweb.agenda.mapper;

import br.com.sdweb.agenda.domain.Autor;
import br.com.sdweb.agenda.controller.httmodel.request.AutorRequest;
import br.com.sdweb.agenda.controller.httmodel.response.AutorResponse;

public class AutorMapper {
    public static Autor to(AutorRequest request) {
        return Autor.builder()
                .nome(request.nome())
                .build();
    }

    public static AutorResponse toResponse(Autor domain) {
        return new AutorResponse(domain.getId(), domain.getNome());
    }
}
