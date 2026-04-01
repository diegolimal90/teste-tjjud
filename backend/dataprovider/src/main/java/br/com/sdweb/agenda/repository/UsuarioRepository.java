package br.com.sdweb.agenda.repository;

import br.com.sdweb.agenda.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
    UserDetails findByUsername(String username);
}
