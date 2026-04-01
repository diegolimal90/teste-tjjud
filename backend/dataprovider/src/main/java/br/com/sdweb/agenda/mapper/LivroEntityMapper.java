package br.com.sdweb.agenda.mapper;

import br.com.sdweb.agenda.domain.Livro;
import br.com.sdweb.agenda.entities.LivroEntity;
import java.util.stream.Collectors;
import java.util.UUID;

public class LivroEntityMapper {
    public static LivroEntity toEntity(Livro domain) {
        if (domain == null) return null;
        return LivroEntity.builder()
                .codl(domain.getId() != null ? UUID.fromString(domain.getId()) : null)
                .titulo(domain.getTitulo())
                .editora(domain.getEditora())
                .edicao(domain.getEdicao())
                .anoPublicacao(domain.getAnoPublicacao())
                .valor(domain.getValor())
                .autores(domain.getAutores() != null ? domain.getAutores().stream().map(AutorEntityMapper::to).collect(Collectors.toSet()) : null)
                .assuntos(domain.getAssuntos() != null ? domain.getAssuntos().stream().map(AssuntoEntityMapper::toEntity).collect(Collectors.toSet()) : null)
                .build();
    }

    public static Livro toDomain(LivroEntity entity) {
        if (entity == null) return null;
        return Livro.builder()
                .id(entity.getCodl() != null ? entity.getCodl().toString() : null)
                .titulo(entity.getTitulo())
                .editora(entity.getEditora())
                .edicao(entity.getEdicao())
                .anoPublicacao(entity.getAnoPublicacao())
                .valor(entity.getValor())
                .autores(entity.getAutores() != null ? entity.getAutores().stream().map(AutorEntityMapper::from).collect(Collectors.toList()) : null)
                .assuntos(entity.getAssuntos() != null ? entity.getAssuntos().stream().map(AssuntoEntityMapper::toDomain).collect(Collectors.toList()) : null)
                .build();
    }
}
