package br.com.sdweb.agenda.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;
import java.math.BigDecimal;

@Entity
@Table(name = "Livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Codl")
    private UUID codl;
    
    @Column(name = "Titulo", length = 40)
    private String titulo;
    
    @Column(name = "Editora", length = 40)
    private String editora;
    
    @Column(name = "Edicao")
    private Integer edicao;
    
    @Column(name = "AnoPublicacao", length = 4)
    private String anoPublicacao;
    
    @Column(name = "Valor", precision = 10, scale = 2)
    private BigDecimal valor;
    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Livro_Autor", 
        joinColumns = { @JoinColumn(name = "Livro_Codl") }, 
        inverseJoinColumns = { @JoinColumn(name = "Autor_CodAu") }
    )
    private Set<AutorEntity> autores = new HashSet<>();
    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Livro_Assunto", 
        joinColumns = { @JoinColumn(name = "Livro_Codl") }, 
        inverseJoinColumns = { @JoinColumn(name = "Assunto_codAs") }
    )
    private Set<AssuntoEntity> assuntos = new HashSet<>();
}
