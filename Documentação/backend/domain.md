# Camada de Domain (Domínio)

A camada de **Domain** é o núcleo central e mais sagrado da aplicação. Ela contém as definições das entidades de negócio e as regras fundamentais que regem o comportamento do sistema, independentemente de como ele é acessado (Web, CLI) ou onde os dados são armazenados (SQL, NoSQL).

## 💎 Pureza e Independência
Na Arquitetura Limpa, o Domínio deve ser o mais "puro" possível. Isso significa que:

-   **Framework-Free**: Não deve haver anotações de frameworks como Spring (@Component, @Service) ou persistência (@Entity, @Table).
-   **Independência de IO**: O domínio não sabe o que é um banco de dados, uma API externa ou um sistema de arquivos.
-   **Estabilidade**: É a camada que menos deve mudar. Mudanças aqui geralmente refletem uma mudança real na regra de negócio da empresa.

## 📦 Componentes do Domínio

### 1. Entidades (Entities)
As entidades são objetos que possuem uma identidade única e encapsulam o estado e o comportamento do negócio.

### 2. Regras de Negócio Invariantes
Lógicas que são verdadeiras para a entidade em qualquer contexto devem morar aqui. 
*Exemplo: Um livro nunca pode ter um valor negativo ou uma edição zero.*

## 🛠️ O papel do Lombok
Embora busquemos a pureza absoluta, utilizamos o **Lombok** para reduzir o ruído visual (boilerplate) de getters, setters e construtores. Isso não viola a arquitetura, pois o Lombok é uma ferramenta de compilação que não vaza para o runtime da aplicação como uma dependência de infraestrutura pesada.

## 🤝 Relacionamento com a Camada Core
A camada de **Core** (onde vivem os Casos de Uso) consome o **Domain**. 

1.  O **Core** solicita dados (via Boundary).
2.  O **DataProvider** entrega objetos de **Domain**.
3.  O **Core** executa a lógica em cima desses objetos de **Domain**.
4.  O **Core** devolve ou persiste esses mesmos objetos.

> [!IMPORTANT]
> **Diferença Crucial**: Enquanto o **DTO** (no Entrypoint) é focado em como os dados são transferidos, a **Entidade de Domínio** é focada em como o negócio funciona e se valida.

---

### Exemplo de Entidade Pura:
```java
public class Livro {
    private String id;
    private String titulo;
    private BigDecimal valor;
    
    // Comportamento intrínseco (Domain Logic)
    public void aplicarDesconto(BigDecimal percentual) {
        if (percentual.compareTo(BigDecimal.ZERO) > 0) {
            this.valor = this.valor.subtract(this.valor.multiply(percentual));
        }
    }
}
```
