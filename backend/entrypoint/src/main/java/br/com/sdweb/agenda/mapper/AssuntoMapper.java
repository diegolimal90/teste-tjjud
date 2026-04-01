package br.com.sdweb.agenda.mapper;

import br.com.sdweb.agenda.domain.Assunto;
import br.com.sdweb.agenda.controller.httmodel.request.AssuntoRequest;
import br.com.sdweb.agenda.controller.httmodel.response.AssuntoResponse;

public class AssuntoMapper {
    public static Assunto toDomain(AssuntoRequest request) {
        return Assunto.builder()
                .descricao(request.descricao())
                .build();
    }

    public static AssuntoResponse toResponse(Assunto domain) {
        return new AssuntoResponse(domain.getId(), domain.getDescricao());
    }
}
