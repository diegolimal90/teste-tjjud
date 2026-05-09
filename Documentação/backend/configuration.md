# Camada de Configuration (Configuração)

A camada de **Configuration** é o "cérebro técnico" do sistema. É o único módulo que tem permissão para conhecer e importar todos os outros módulos do projeto (`core`, `entrypoint`, `dataprovider`, `boundary`). 

Seguindo os princípios da Arquitetura Limpa, este é um módulo propositalmente **"sujo"**, onde o framework (Spring Boot) é carregado e utilizado para amarrar todas as pontas isoladas da aplicação.

## 🧠 Orquestração de Dependências
Como o seu **Core** é composto por classes puras (POJOs) sem anotações de infraestrutura, é nesta camada que realizamos a "mágica" da Injeção de Dependência via `@Bean`.

-   **Instanciação**: Criamos instâncias dos Casos de Uso (`SalvarLivroUseCase`, etc).
-   **Conexão**: Injetamos as implementações concretas (DataProviders) nas interfaces exigidas pelo Core (Boundary Out).
-   **Exposição**: Deixamos o Use Case pronto para ser injetado nos Controllers do Entrypoint.

## 🛠️ Responsabilidades Transversais
Além de orquestrar a injeção, este módulo centraliza as configurações técnicas que afetam todo o ecossistema:

### 1. Segurança (Spring Security + JWT)
-   Configuração de filtros de autenticação (`SecurityFilter`).
-   Definição de rotas públicas vs. protegidas.
-   Gestão de Tokens JWT e criptografia de senhas.

### 2. Banco de Dados e Persistência
-   Configurações do Hibernate, Contextos de Transação e Pool de Conexões.
-   Integração com o JPA do módulo DataProvider.

### 3. Exception Handling (Tratamento Global)
-   Implementação do `RestControllerAdvice` para capturar exceções das outras camadas e formatá-las no JSON padrão de erro para o frontend.

### 4. OpenApi / Swagger
-   Documentação automática dos endpoints para facilitar o consumo da API.

## 🏗️ Estrutura do Módulo Maven
O `pom.xml` deste módulo é o mais rico, pois ele agrega as dependências de todos os outros pacotes e as bibliotecas pesadas de infraestrutura.

> [!IMPORTANT]
> **Ponto Único de Falha**: Por ser o orquestrador, se algo estiver errado na configuração (DI), a aplicação nem sequer subirá. No entanto, o erro estará isolado aqui, sem afetar a lógica de negócio no Core.

---

### Exemplo de Configuração de Bean:
```java
@Configuration
public class LivroBeanConfig {

    @Bean
    public SalvarLivroIn salvarLivroIn(LivroOut livroOut) {
        // Core sendo instanciado e recebendo sua dependência de infra
        return new SalvarLivroUseCase(livroOut);
    }
}
```
