package br.com.sdweb.agenda;

import br.com.sdweb.agenda.boundary.out.AssuntoOut;
import br.com.sdweb.agenda.domain.Assunto;
import br.com.sdweb.agenda.entities.AssuntoEntity;
import br.com.sdweb.agenda.mapper.AssuntoEntityMapper;
import br.com.sdweb.agenda.repository.AssuntoRepository;
import exceptions.Errors;
import exceptions.OutException;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AssuntoDataProvider implements AssuntoOut {

    private final AssuntoRepository repository;

    @Override
    public Assunto salvar(Assunto assunto) {
        try {
            AssuntoEntity entity = AssuntoEntityMapper.toEntity(assunto);
            AssuntoEntity saved = repository.save(entity);
            return AssuntoEntityMapper.toDomain(saved);
        } catch (Exception e) {
            throw new OutException("Falha de comunicacao ao tentar persistir", e, Errors.ERROR0002);
        }
    }

    @Override
    public Assunto atualizar(Assunto assunto) {
        try {
            AssuntoEntity entity = repository.findById(UUID.fromString(assunto.getId()))
                    .orElseThrow(() -> new OutException("Assunto nao localizado na base de dados (ID: " + assunto.getId() + ")", Errors.ERROR0005));
            entity.setDescricao(assunto.getDescricao());
            AssuntoEntity salvo = repository.save(entity);
            return AssuntoEntityMapper.toDomain(salvo);
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
    public Assunto buscarPorId(String id) {
        try {
            AssuntoEntity entity = repository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new OutException("Assunto nao localizado na base de dados (ID: " + id + ")", Errors.ERROR0007));
            return AssuntoEntityMapper.toDomain(entity);
        } catch (OutException oe) {
            throw oe;
        } catch (Exception e) {
            throw new OutException("Falha de comunicacao ao tentar buscar por id", e, Errors.ERROR0007);
        }
    }

    @Override
    public List<Assunto> listar() {
        try {
            List<AssuntoEntity> entities = repository.findAll();
            return entities.stream().map(AssuntoEntityMapper::toDomain).toList();
        } catch (Exception e) {
            throw new OutException("Falha de comunicacao ao tentar listar", e, Errors.ERROR0006);
        }
    }
}
