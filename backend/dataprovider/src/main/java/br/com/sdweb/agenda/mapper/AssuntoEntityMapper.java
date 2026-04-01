package br.com.sdweb.agenda.mapper;

import br.com.sdweb.agenda.domain.Assunto;
import br.com.sdweb.agenda.entities.AssuntoEntity;
import java.util.UUID;

public class AssuntoEntityMapper {

    public static AssuntoEntity toEntity(Assunto domain) {
        if (domain == null) return null;
        return AssuntoEntity.builder()
                .codAs(domain.getId() != null ? UUID.fromString(domain.getId()) : null)
                .descricao(domain.getDescricao())
                .build();
    }

    public static Assunto toDomain(AssuntoEntity entity) {
        if (entity == null) return null;
        return Assunto.builder()
                .id(entity.getCodAs() != null ? entity.getCodAs().toString() : null)
                .descricao(entity.getDescricao())
                .build();
    }
}
