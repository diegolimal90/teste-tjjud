# Camada de Core (Coração / Casos de Uso / Interactors)

A camada de **Core** é o cérebro da aplicação. Nela residem os **Casos de Uso** (Use Cases), que representam as ações que o sistema pode realizar. É aqui que os requisitos de negócio são transformados em lógica executável.

Esta camada orquestra o fluxo de dados de e para as entidades, utilizando as regras de negócio do Domínio para atingir os objetivos do caso de uso.

## 🧠 Independência Total de Frameworks
Seguindo o princípio mais rigoroso da Arquitetura Limpa, o Core não deve ter **nenhuma** dependência de frameworks externos (como Spring, Quarkus ou Hibernate).

-   **Sem Anotações**: Você não verá `@Service`, `@Component` ou `@Autowired` aqui.
-   **Injeção via Construtor**: Todas as dependências são passadas via construtor, permitindo que a classe seja testada em isolamento total com `JUnit` puro e `Mockito` sem precisar subir o contexto do Spring.
-   **Pureza de Código**: O código é Java puro, garantindo que a lógica de negócio dure muito mais que qualquer moda de framework.

## 🏗️ Estrutura da Camada

### 1. Use Cases (Interactors)
Cada classe de Use Case deve focar em uma única tarefa ou um conjunto muito pequeno de tarefas relacionadas.
*Exemplo: `SalvarLivroUseCase`, `GerarRelatorioUseCase`.*

### 2. Fluxo de Execução
O Use Case segue um roteiro lógico:
1. Recebe um objeto de **Domain** (vindo do Entrypoint via Boundary In).
2. Valida **Regras de Negócio de Processo** (Ex: "Verificar se o valor do livro é positivo").
3. Interage com o **Domain** para disparar lógicas internas da entidade.
4. Persiste ou consulta dados através das interfaces do **Boundary Out** (sem saber se é um Banco SQL ou uma API).
5. Retorna o resultado para o mundo exterior.

## 🔄 Orquestração e Boundary
O Core é o centro de uma "Inversão de Controle":
-   **Implementa** a interface `Boundary In` (para ser chamado pelo Entrypoint).
-   **Consome** a interface `Boundary Out` (para mandar o DataProvider).

## ⚠️ Diferença entre Regra de Negócio e Lógica de Fluxo
-   **Regra de Negócio (Domain)**: "Um livro deve ter um autor". (Vive na Entidade).
-   **Lógica de Fluxo (Core)**: "Ao salvar um livro, valide o preço e então mande o banco persistir". (Vive no Use Case).

---

### Exemplo de implementação no Core:
```java
public class SalvarLivroUseCase implements SalvarLivroIn {
    
    private final LivroOut livroOut; // Interface do Boundary Out

    public SalvarLivroUseCase(LivroOut livroOut) {
        this.livroOut = livroOut;
    }

    @Override
    public Livro salvar(Livro livro) {
        // Validação de negócio no Core
        if (livro.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Preço inválido", Errors.ERROR0001);
        }
        
        return livroOut.salvar(livro); // Delega a persistência
    }
}
```
