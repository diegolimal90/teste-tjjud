# Camada de Entrypoint (Interface de Entrada)

A camada de **Entrypoint** é o ponto de contato inicial da aplicação com o mundo exterior. Sua principal responsabilidade é recepcionar requisições, validar o formato básico dos dados e delegar a execução para a camada de regra de negócio (`core`) através das interfaces definidas no `boundary`.

Nesta arquitetura, o Entrypoint é tratado como um detalhe de infraestrutura que pode ser substituído ou expandido sem afetar o coração do sistema.

## 🚀 Além do REST API
Embora o uso mais comum em aplicações modernas seja a implementação de **Controllers REST**, a camada de Entrypoint é agnóstica ao protocolo e pode hospedar diversos tipos de gatilhos de entrada:

-   **HTTP/REST**: Controllers Spring MVC que expõem endpoints JSON.
-   **Messaging/Consumers**: Listeners que consomem mensagens de filas ou tópicos (RabbitMQ, Kafka, SQS).
-   **Jobs / Schedulers**: Classes anotadas com `@Scheduled` que iniciam processos baseados em tempo.
-   **SOAP**: Webservices legados ou integrações formais via XML.

## 🏗️ Estrutura da Camada

### 1. Controllers
São os orquestradores da entrada. Eles não devem conter lógica de negócio. Sua função é:
- Receber a requisição.
- Converter os dados de entrada para o formato aceito pelo Core (Domain).
- Chamar o Use Case pertinente.
- Retornar a resposta com o status code apropriado (Ex: 200 OK, 201 Created, 404 Not Found).

### 2. DTOs (Data Transfer Objects)
Localizados nos pacotes `request` e `response`, os DTOs servem como um "contrato de segurança".
-   **Isolamento**: Eles impedem que alterações no BD ou no Domain quebrem quem consome a API (e vice-versa).
-   **HTTP Model**: São otimizados para o que a interface precisa ver, não necessariamente como os dados estão guardados.

## 🔄 A Importância Crucial do Mapper
O **Mapper** é a peça fundamental que garante a independência do Core. Sem ele, a arquitetura "vazaria" detalhes externos para dentro do negócio.

### Por que usar Mappers?
1.  **Desacoplamento Rigoroso**: O Core trabalha com objetos de **Domínio**. O Entrypoint trabalha com **DTOs**. O Mapper faz a tradução bidirecional entre eles.
2.  **Proteção do Domínio**: Se adicionarmos um campo extra em um JSON de resposta para facilitar a vida do Frontend, fazemos isso no DTO e no Mapper. O nosso Domínio permanece "limpo" e focado apenas nas regras de negócio.
3.  **Conversão de Tipos**: É no Mapper que transformamos Strings vindas da Web em Enums complexos que o sistema utiliza internamente.

> [!IMPORTANT]
> **Regra de Ouro**: Um Controller **nunca** deve retornar uma Entidade de Domínio diretamente. Ele deve sempre mapear o resultado do Core para um DTO de Resposta antes de enviá-lo ao cliente.

---

### Exemplo de Fluxo no Entrypoint:
1.  **Recebe**: `LivroRequest` (JSON).
2.  **Mapeia**: `LivroMapper.toDomain(request)` -> Gera um objeto `Livro` (Domain).
3.  **Executa**: Chama `salvarLivroIn.salvar(livro)`.
4.  **Mapeia**: `LivroMapper.toResponse(resultado)` -> Gera um `LivroResponse` (DTO).
5.  **Responde**: HTTP 201 com o JSON do `LivroResponse`.

#### ✅ Exemplo de Sucesso (201 Created):
```json
{
  "correlationId": "b290f1ee-6c54-4b01-90e6-d701748f0851",
  "dateResponse": "2026-04-02T21:53:45-03:00",
  "status": "201",
  "message": "Requisição processada com sucesso",
  "data": [{
    "id": "b290f1ee-6c54-4b01-90e6-d701748f0851",
    "titulo": "Arquitetura Limpa",
    "editora": "Alta Books",
    "edicao": 1,
    "anoPublicacao": "2019",
    "valor": 75.90
  }]
}
```

#### ❌ Exemplo de Erro (400 Bad Request):
Caso o valor monetário venha negativo ou inválido, a resposta segue um padrão de erro:
```json
{
  "correlationId": "b290f1ee-6c54-4b01-90e6-d701748f0851",
  "dateResponse": "2026-04-02T21:53:45-03:00",
  "status": "400",
  "message": "Requisição inválida",
  "error": [{
    "code": "0001",
    "message": "Valor monetario incorreto ou negativo",
    "field": "valor"
  }]
}
```
