# Camada de Boundary (Contratos / Interfaces / Portas)

A camada de **Boundary** define as fronteiras do sistema. Ela é composta exclusivamente por **Interfaces** que atuam como "Contratos", permitindo a comunicação entre o mundo exterior e as regras de negócio puras do Core.

Esta camada é vital para manter o desacoplamento, garantindo que o seu sistema seja flexível e fácil de testar.

## 🚪 O Conceito de Portas (Ports)
Na Arquitetura Limpa, as interfaces de Boundary são divididas em dois grupos principais:

### 1. Portas de Entrada (`boundary.in`)
As interfaces em `in` definem as ações que a aplicação pode receber.
-   **Quem chama?** O **Entrypoint** (REST Controller, Jobs, Consumers).
-   **Quem implementa?** O **Core** (Use Cases).
-   **Exemplo**: `SalvarLivroIn`. O Controller chama o método `salvar()`, e o Use Case executa a lógica.

### 2. Portas de Saída (`boundary.out`)
As interfaces em `out` definem as necessidades que o Core tem de recursos externos.
-   **Quem chama?** O **Core** (Use Cases).
-   **Quem implementa?** O **DataProvider** (Banco de Dados, consumo de APIs).
-   **Exemplo**: `LivroOut`. O Use Case solicita que o livro seja persistido, e o DataProvider atende o pedido usando JPA ou qualquer outra tecnologia.

## ⚖️ A Lei da Independência
As interfaces no Boundary devem utilizar apenas tipos **nativos do Java** e as entidades do **Domínio**. 

1.  **Sem Frameworks**: Você nunca verá anotações do Spring ou bibliotecas de terceiros aqui.
2.  **Agnosticismo**: O Boundary não sabe se quem o chama é uma API REST ou se quem persiste é um banco SQL. Ele apenas define o *contrato* (o que entra e o que sai).

## 🏆 Vantagens de utilizar o Boundary
-   **Testabilidade**: Você pode testar o Core mockando facilmente as interfaces de `out`.
-   **Substituibilidade**: Você pode trocar o seu Banco de Dados (DataProvider) sem que o Core precise ser recompilado, desde que o novo DataProvider respeite a interface no Boundary.
-   **Clareza**: Ao abrir o módulo `boundary`, você tem uma visão completa de todas as funcionalidades (Use Cases) do sistema através das assinaturas dos métodos.

---

### Exemplo de Interface (Porta):
```java
// Contrato puro que define a entrada (Port)
public interface SalvarLivroIn {
    Livro salvar(Livro livro);
}
```
