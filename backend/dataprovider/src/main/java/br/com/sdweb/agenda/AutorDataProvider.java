package br.com.sdweb.agenda;

import br.com.sdweb.agenda.boundary.out.AutorOut;
import br.com.sdweb.agenda.domain.Autor;
import br.com.sdweb.agenda.entities.AutorEntity;
import br.com.sdweb.agenda.mapper.AutorEntityMapper;
import lombok.RequiredArgsConstructor;
import br.com.sdweb.agenda.repository.AutorRepository;
import exceptions.Errors;
import exceptions.OutException;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AutorDataProvider implements AutorOut {

    private final AutorRepository repository;

    @Override
    public Autor salvar(Autor autor) {
        try {
            AutorEntity entity = AutorEntityMapper.to(autor);
            AutorEntity salvo = repository.save(entity);
            return AutorEntityMapper.from(salvo);
        } catch (Exception e) {
            throw new OutException("Falha de comunicacao ao tentar persistir", e, Errors.ERROR0002);
        }
    }

    @Override
    public Autor atualizar(Autor autor) {
        try {
            AutorEntity entity = repository.findById(UUID.fromString(autor.getId()))
                    .orElseThrow(() -> new OutException("Autor nao localizado na base de dados para atualizacao (ID: " + autor.getId() + ")", Errors.ERROR0005));
            
            // Atualiza apenas os campos permitidos
            entity.setNome(autor.getNome());
            
            AutorEntity salvo = repository.save(entity);
            return AutorEntityMapper.from(salvo);
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
    public Autor buscarPorId(String id) {
        try {
            AutorEntity entity = repository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new OutException("Autor nao localizado na base de dados (ID: " + id + ")", Errors.ERROR0007));
            return AutorEntityMapper.from(entity);
        } catch (OutException oe) {
            throw oe;
        } catch (Exception e) {
            throw new OutException("Falha de comunicacao ao tentar buscar por id", e, Errors.ERROR0007);
        }
    }

    @Override
    public List<Autor> listar() {
        try {
            List<AutorEntity> entities = repository.findAll();
            return entities.stream().map(AutorEntityMapper::from).toList();
        } catch (Exception e) {
            throw new OutException("Falha de comunicacao ao tentar listar", e, Errors.ERROR0006);
        }
    }
}
