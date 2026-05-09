# Camada de Commons (Utilitários Compartilhados)

A camada de **Commons** é um módulo utilitário e transversal que pode ser acessado por qualquer outra camada do projeto. Seu objetivo é centralizar códigos genéricos e infraestrutura comum que não pertencem exclusivamente a nenhuma regra de negócio específica.

Assim como o Domínio, esta camada deve ser extremamente leve e livre de dependências de frameworks pesados, garantindo que sua importação não "suje" as outras camadas.

## 📦 Componentes do Commons

### 1. Exceções Customizadas (Custom Exceptions)
Padronizamos o tratamento de erros em todo o sistema através de classes específicas:

-   **BusinessException**: Utilizada principalmente no **Core**. Representa uma violação de regra de negócio (ex: "Saldo insuficiente", "Valor negativo").
-   **OutException**: Utilizada no **DataProvider**. Representa uma falha técnica ou de comunicação externa (ex: "Erro ao conectar no banco", "Timeout na API externa").
-   **MethodNotImplemented**: Exceção técnica para sinalizar métodos em desenvolvimento.

### 2. Gestão de Erros (Enums)
Centralizamos todos os códigos e mensagens de erro no enum **`Errors`**. 
- Isso permite que o Frontend receba códigos únicos (ex: `0001`, `0002`) e que o Backend mantenha a manutenção das mensagens em um único lugar.

### 3. DTOs Técnicos
Objetos simples para transferência de dados que não representam o negócio, como o **`ErrorDTO`**, que é a estrutura padrão enviada no corpo das respostas de erro da API.

## 🤝 O Papel na "The Dependency Rule"
Embora a Arquitetura Limpa pregue que as dependências devem apontar para dentro (Domain/Core), o **Commons** é uma exceção aceitável por conter apenas ferramentas técnicas agnósticas. Ele serve para evitar a duplicação de boilerplate em diferentes módulos Maven.

> [!WARNING]
> **O que NÃO colocar no Commons**:
> - Regras de negócio complexas.
> - Dependências do Spring Boot ou Hibernate.
> - Classes que importam do `core` ou `dataprovider` (Isso causaria dependência circular).

---

### Exemplo de Uso de Exceção do Commons:
```java
// No Core
if (livro.getValor() < 0) {
    throw new BusinessException("Valor inválido", Errors.ERROR0001);
}

// No DataProvider
try {
    repository.save(entity);
} catch (Exception e) {
    throw new OutException("Falha na persistência", e, Errors.ERROR0002);
}
```
