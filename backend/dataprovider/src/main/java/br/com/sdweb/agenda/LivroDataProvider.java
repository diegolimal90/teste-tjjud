package br.com.sdweb.agenda;

import br.com.sdweb.agenda.boundary.out.LivroOut;
import br.com.sdweb.agenda.domain.Assunto;
import br.com.sdweb.agenda.domain.Autor;
import br.com.sdweb.agenda.domain.Livro;
import br.com.sdweb.agenda.entities.AssuntoEntity;
import br.com.sdweb.agenda.entities.AutorEntity;
import br.com.sdweb.agenda.entities.LivroEntity;
import br.com.sdweb.agenda.mapper.LivroEntityMapper;
import br.com.sdweb.agenda.repository.AssuntoRepository;
import br.com.sdweb.agenda.repository.AutorRepository;
import br.com.sdweb.agenda.repository.LivroRepository;
import exceptions.BusinessException;
import exceptions.Errors;
import exceptions.OutException;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class LivroDataProvider implements LivroOut {

    private final LivroRepository repository;
    private final AssuntoRepository assuntoRepository;
    private final AutorRepository autorRepository;

    @Override
    public Livro salvar(Livro livro) {
        try {
            LivroEntity entity = LivroEntityMapper.toEntity(livro);

            if (entity.getAssuntos() != null) {
                Set<AssuntoEntity> assuntos = new HashSet<>();
                for (AssuntoEntity assunto : entity.getAssuntos()) {
                    if (assunto.getCodAs() == null) {
                        throw new BusinessException("Assunto invalido", Errors.ERROR0008);
                    }
                    assuntos.add(assuntoRepository.findById(assunto.getCodAs())
                            .orElseThrow(() -> new OutException("Assunto nao localizado na base de dados (ID: " + assunto.getCodAs() + ")", Errors.ERROR0007)));
                }
                entity.setAssuntos(assuntos);
            }

            if (livro.getAutores() != null) {
                Set<AutorEntity> autores = new HashSet<>();
                for (AutorEntity autor : entity.getAutores()) {
                    if (autor.getCodAu() == null) {
                        throw new BusinessException("Autor invalido", Errors.ERROR0009);
                    }
                    autores.add(autorRepository.findById(autor.getCodAu())
                            .orElseThrow(() -> new OutException("Autor nao localizado na base de dados (ID: " + autor.getCodAu() + ")", Errors.ERROR0007)));
                }
                entity.setAutores(autores);
            }
            LivroEntity saved = repository.save(entity);
            return LivroEntityMapper.toDomain(saved);
        } catch (Exception e) {
            throw new OutException("Falha de comunicacao ao tentar persistir", e, Errors.ERROR0002);
        }
    }

    @Override
    public Livro atualizar(Livro livro) {
        try {
            LivroEntity entity = repository.findById(UUID.fromString(livro.getId()))
                    .orElseThrow(() -> new OutException("Livro nao localizado na base de dados (ID: " + livro.getId() + ")", Errors.ERROR0005));
            entity.setTitulo(livro.getTitulo());
            entity.setEditora(livro.getEditora());
            entity.setEdicao(livro.getEdicao());
            entity.setAnoPublicacao(livro.getAnoPublicacao());
            entity.setValor(livro.getValor());
            // Atualizar listas autores/assuntos dependeria do Mapper reconstruí-los
            
            LivroEntity salvo = repository.save(entity);
            return LivroEntityMapper.toDomain(salvo);
        } catch (OutException oe) {
            throw oe;
        } catch (Exception e) {
            throw new OutException("Falha de comunicacao ao tentar atualizar", e, Errors.ERROR0005);
        }
    }

    @Override
    public void deletar(String id) {
        try {
            repository.deleteById(UUID.fromString(id));
        } catch (Exception e) {
            throw new OutException("Falha de comunicacao ao tentar deletar", e, Errors.ERROR0004);
        }
    }

    @Override
    public Livro buscarPorId(String id) {
        try {
            LivroEntity entity = repository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new OutException("Livro nao localizado na base de dados (ID: " + id + ")", Errors.ERROR0007));
            return LivroEntityMapper.toDomain(entity);
        } catch (OutException oe) {
            throw oe;
        } catch (Exception e) {
            throw new OutException("Falha de comunicacao ao tentar buscar por id", e, Errors.ERROR0007);
        }
    }

    @Override
    public List<Livro> listar() {
        try {
            List<LivroEntity> entities = repository.findAll();
            return entities.stream().map(LivroEntityMapper::toDomain).toList();
        } catch (Exception e) {
            throw new OutException("Falha de comunicacao ao tentar listar", e, Errors.ERROR0006);
        }
    }
}
