package br.com.sdweb.agenda.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;

@Entity
@Table(name = "Autor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CodAu")
    private UUID codAu;

    @Column(name = "Nome", length = 40)
    private String nome;

    @ManyToMany(mappedBy = "autores")
    private Set<LivroEntity> livros = new HashSet<>();
}
