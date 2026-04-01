package br.com.sdweb.agenda.repository;

import br.com.sdweb.agenda.entities.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<LivroEntity, UUID> {
}
