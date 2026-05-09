# Camada de DataProvider (Provedor de Dados)

A camada de **DataProvider** é responsável por toda a comunicação com recursos externos à aplicação, como Bancos de Dados, APIs de terceiros, sistemas de arquivos ou serviços de mensageria. 

Nesta arquitetura, ela é tratada como um detalhe de implementação. O sistema não "sabe" que usa PostgreSQL ou JPA; ele apenas sabe que existe um contrato a ser cumprido.

## 🔄 Inversão de Dependência (IoC)
O DataProvider não dita as regras. Ele **implementa** as interfaces (portas de saída) definidas na camada de `boundary`. 

-   O **Core** define: *"Eu preciso de um método que salve um Livro"*.
-   O **DataProvider** entrega: *"Eu sei salvar um Livro usando o Hibernate no Postgres"*.

Isso permite trocar o banco de dados inteiro apenas criando um novo DataProvider, sem tocar em uma única linha de lógica de negócio.

## 🏗️ Estrutura da Camada

### 1. DataProviders (Implementações)
São as classes anotadas com `@Component` (ou `@Service`) que implementam as interfaces do `boundary.out`. Elas orquestram a chamada aos Repositories e fazem o tratamento de erros de infraestrutura.

### 2. Repositories (Spring Data JPA)
Interfaces que estendem `JpaRepository`. Elas são a ponte direta com o Spring Data para realizar as operações de CRUD no banco de dados.

### 3. Entities (Modelos de Persistência)
Classes anotadas com `@Entity` e `@Table`. 
-   **Importante**: Estas classes **não** são as mesmas que o Domínio. Elas são otimizadas para o banco de dados (chaves estrangeiras, colunas, tipos específicos do SQL).

## 🔄 O Papel do EntityMapper
Assim como no Entrypoint, utilizamos Mappers aqui para converter os dados:

1.  **Domain para Entity**: Quando vamos salvar, convertemos o objeto de **Domínio** para uma **Entidade JPA**.
2.  **Entity para Domain**: Quando buscamos dados, convertemos a **Entidade JPA** de volta para um objeto de **Domínio** antes de devolvê-lo para o Core.

Isso garante que anotações como `@Column` ou `@Id` nunca vazem para dentro da sua regra de negócio.

## ⚠️ Tratamento de Erros de Infraestrutura
O DataProvider é responsável por capturar exceções específicas (como `SQLException` ou `DataAccessException`) e traduzi-las para exceções de saída conhecidas pelo sistema (ex: `OutException`), mantendo o log técnico mas devolvendo uma mensagem compreensível.

---

### Exemplo de Fluxo no DataProvider:
1.  **Recebe**: Objeto `Livro` (Domain).
2.  **Mapeia**: `LivroEntityMapper.toEntity(livro)` -> Gera um `LivroEntity` (JPA).
3.  **Persiste**: `repository.save(entity)`.
4.  **Mapeia**: `LivroEntityMapper.toDomain(saved)` -> Converte o resultado de volta para o Domínio.
5.  **Retorna**: O objeto `Livro` processado.
