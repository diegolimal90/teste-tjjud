package br.com.sdweb.agenda.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;

@Entity
@Table(name = "Assunto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssuntoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codAs")
    private UUID codAs;

    @Column(name = "Descricao", length = 20)
    private String descricao;

    @ManyToMany(mappedBy = "assuntos")
    private Set<LivroEntity> livros = new HashSet<>();
}
