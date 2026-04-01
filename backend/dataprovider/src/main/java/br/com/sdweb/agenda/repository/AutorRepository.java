package br.com.sdweb.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sdweb.agenda.entities.AutorEntity;

import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, UUID> {
}
