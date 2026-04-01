package br.com.sdweb.agenda.repository;

import br.com.sdweb.agenda.entities.AssuntoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssuntoRepository extends JpaRepository<AssuntoEntity, UUID> {
}
