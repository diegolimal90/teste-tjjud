# Estrutura do Projeto Frontend (React + Vite + TypeScript)

O frontend foi desenvolvido utilizando o padrão de **Arquitetura em Camadas (Layered Architecture)** e o **Service Layer Pattern**. Essa abordagem garante que a lógica de negócio e as chamadas de rede fiquem isoladas da lógica de renderização da interface, facilitando a manutenção e testes.


## 🏗️ Organização de Pastas (`/src`)

Abaixo detalhamos a responsabilidade de cada diretório dentro da pasta `src`:

### 1. `api` (Infraestrutura de Rede)
**Papel:** Centralizar a configuração do cliente HTTP.
-   Contém o arquivo `api.ts` que configura o **Axios** com a `baseURL` do backend e interceptores para injetar o Token JWT automaticamente em cada requisição.

### 2. `assets` (Recursos Estáticos)
**Papel:** Armazenar imagens, ícones SVG e outros arquivos estáticos que serão importados pelos componentes React.

### 3. `components` (Componentes Reutilizáveis)
**Papel:** Guardar peças de UI que são usadas em múltiplas partes do sistema.
-   **Layout.tsx**: O "wrapper" que define a estrutura da página (Sidebar + Conteúdo).
-   **Sidebar.tsx**: O menu de navegação lateral.

### 4. `context` (Estado Global)
**Papel:** Gerenciar estados que precisam ser acessados por toda a aplicação.
-   **AuthContext.tsx**: Gerencia o estado de autenticação (token, login, logout) e persiste os dados no `localStorage`.

### 5. `pages` (Vistas da Aplicação)
**Papel:** Cada pasta aqui representa uma "tela" completa do sistema.
-   **Login**: Tela de autenticação inicial.
-   **Home**: Dashboard de boas-vindas.
-   **Autores / Assuntos / Livros**: Telas de gestão (CRUD) completas.
-   **Relatorios**: Tela para geração e visualização de PDFs via Jasper.

### 6. `routes` (Roteamento)
**Papel:** Definir a malha de caminhos da aplicação.
-   **AppRouter.tsx**: Configura o `react-router-dom`, definindo quais rotas são públicas (Login) e quais são protegidas (precisam de token).

### 7. `services` (Lógica de Dados)
**Papel:** Isolar as chamadas de API da lógica de UI.
-   **dataService.ts**: Contém todas as funções que realizam GET, POST, PUT e DELETE, retornando Promessas tipadas para os componentes.

### 8. `types` (Tipagem TypeScript)
**Papel:** Garantir a segurança do código através de interfaces.
-   Contém as definições de `Autor`, `Assunto` e `Livro`, espelhando o modelo de dados do backend.

## 9. `store` (Redux Toolkit)
**Papel:** Gerenciar estado global.
-   Contém as definições de `store`, `slices`, `thunks` e `hooks` para o gerenciamento de estado global.

> [!TIP]
> A grande vantagem dessa arquitetura é que, por termos isolado as chamadas de API em uma camada de **Service**, a migração para qualquer biblioteca de estado (Redux, Zustand, Recoil) não exige a reescrita de uma única linha de lógica de rede.
---

## 🛠️ Tecnologias e Padrões
-   **Bootstrap 5**: Utilizado de forma nativa para estilização rápida e responsiva.
-   **Lucide React**: Biblioteca de ícones moderna e leve.
-   **TypeScript**: Tipagem estrita para evitar erros em tempo de execução.
-   **Axios Interceptors**: Garante que o usuário nunca precise passar o token manualmente em cada chamada de API.
-   **Redux Toolkit**: Gerenciamento de estado global.

## 🏛️ Padrões e Princípios Aplicados

### 1. Service Layer Pattern
Toda a lógica de infraestrutura de rede (Axios) e as chamadas específicas aos endpoints residem na pasta `services/`. Os componentes de UI nunca chamam o Axios diretamente, garantindo que, se precisarmos trocar a biblioteca de API, alteraremos apenas um lugar.

### 2. Provider Pattern (Context API)
Utilizamos o **Context API** do React para gerenciar o estado global de autenticação. Isso evita o "Prop Drilling" (passar dados por muitos níveis de componentes) e centraliza a segurança.

### 3. Separation of Concerns (SoC)
- **UI**: Reside em `components/` e `pages/`.
- **Lógica de Dados**: Reside em `services/`.
- **Estado Global**: Reside em `context/`.
- **Contratos**: Residem em `types/`.

---

### Fluxo de uma Requisição:
1. O **Componente** (ex: `AutoresPage`) solicita dados ao **Service**.
2. O **Service** chama o cliente do pacote **api**.
3. A **api** (Axios) injeta o token do **AuthContext**.
4. O resultado volta tipado conforme definido em **types** para ser renderizado na tela.

---
