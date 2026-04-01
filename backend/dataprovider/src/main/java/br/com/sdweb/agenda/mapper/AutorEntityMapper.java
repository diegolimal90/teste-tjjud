package br.com.sdweb.agenda.mapper;

import br.com.sdweb.agenda.domain.Autor;
import br.com.sdweb.agenda.entities.AutorEntity;

import java.util.Optional;
import java.util.UUID;

public class AutorEntityMapper {

    public static AutorEntity to(Autor autor) {
        return Optional.ofNullable(autor)
                .map(source -> AutorEntity.builder()
                        .codAu(Optional.ofNullable(source.getId())
                                .map(UUID::fromString)
                                .orElse(null)
                        )
                        .nome(source.getNome())
                        .build())
                .orElse(null);
    }

    public static Autor from(AutorEntity entity) {
        return Optional.ofNullable(entity)
                .map(s -> Autor.builder()
                        .id(Optional.ofNullable(s.getCodAu())
                                .map(UUID::toString)
                                .orElse(null))
                        .nome(s.getNome())
                        .build())
                .orElse(null);
    }

}
