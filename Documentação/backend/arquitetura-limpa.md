# Sistema de Gestão de Livros - TJJ

Projeto completo de gestão de livros desenvolvido com foco em Clean Architecture, segurança robusta e interface moderna.

## 🛠️ Tecnologias Utilizadas

### Backend
- **Core**: Java 21 & Spring Boot 3.2.7
- **Segurança**: Spring Security & JWT (JSON Web Token)
- **Persistência**: Spring Data JPA & Hibernate
- **Banco de Dados**: PostgreSQL
- **Relatórios**: Jasper Reports (Geração de PDFs)
- **Build Tool**: Maven (Projeto Multi-módulo)

### Frontend
- **Framework**: React (Vite)
- **Linguagem**: TypeScript
- **Estilização**: Bootstrap 5 (Native Dark Mode)
- **Comunicação**: Axios
- **Ícones**: Lucide React
- **Navegação**: React Router Dom

### Infraestrutura / DevOps
- **Containerização**: Docker & Docker Compose

---

# Arquitetura Limpa (Clean Architecture)

O projeto deve ser organizado em um formato multi-módulo do Maven (`commons`, `configuration`, `core`, `dataprovider`, `domain`, `entrypoint`, `boundary`), adotando de forma exemplar os princípios da **Arquitetura Limpa** (Clean Architecture).

Abaixo detalhamos a responsabilidade de cada um dos módulos e como as regras de dependência foram cuidadosamente configuradas (`pom.xml` de cada projeto) para manter a aplicação isolada de frameworks e ferramentas de banco de dados.

## 1. `boundary` (Contratos / Interfaces)
**Papel:** Definir rigorosamente os contratos de entrada e saída da aplicação.
Esta é a camada onde escrevemos as interfaces que ditam como o core interage com o "mundo exterior". Ela contém o conceito de isolamento do core com as outras camadas.
- **Dependências:** Este módulo é agnóstico tecnológico: sem referências ao Spring e sem plugins e bancos, dependendo estritamente de classes nativas Java e do módulo `core`.

## 2. `core` (Regras de Negócio / Domain & Use Cases)
**Papel:** Abrigar toda a lógica do negócio (Business Rules) e o domínio rico da aplicação.
É o coração do sistema, de onde vem o nome *core*. Seguindo as restrições arquiteturais, esta camada não deve possuir conhecimento **nenhum** sobre Web, REST, bibliotecas JSON ou Base de Dados.
- **Implementação:** Contém as Entidades intrínsecas ao negócio e a implantação concreta dos Casos de Uso (interactors). 
- **Inversão de Dependência:** Quando o `core` necessita persistir, atualizar, consultar ou excluir uma informação, ele invoca os métodos declarados no `boundary`, abstraindo totalmente *onde* ou *como* esses procedimentos ocorrem sob o capô.
- **Dependências:** Na restrição dos plugins Maven, este módulo só enxerga o `boundary` (por precisar assinar seus contratos), o `commons` (para utilitários) e o `domain` (por ser responsavel e obter as informações de negocio).

## 3. `entrypoint` (Interface de Entrada)
**Papel:** Ser a porta de recepção para chamadas do meio externo demandando interações com a aplicação.
Aqui é o lar dos Controllers REST e rotas HTTP (assim como pode um dia hospedar escutadores de filas ou gRPC). O Entrypoint recebe um chamado vindo da web (ex: um JSON da interface gráfica), converte isso para os objetos que a sua regra de negócios exige, e despacha a requisição chamando o contrato no `boundary`.
- **Dependências:** Assumindo forte acoplamento com tecnologias de API, como o `spring-boot-starter-web` e naturalmente dependendo das interfaces disponíveis no `boundary`. **Regra primária:** Ele só se comunica com as portas para acionar o core, nunca diretamente ao Banco de Dados.

## 4. `dataprovider` (Provedor de Dados)
**Papel:** Prover capacidade técnica para infraestrutura, realizando persistências de dados na ponta ou consumindo APIs externas.
É nestes pacotes que residem as implementações reais e palpáveis das interfaces das "portas de saída" (definidas genericamente lá na camada de `boundary`).
- **Dependência de Infraestrutura:** Toda tecnologia voltada para banco de dados está enclausurada e é dependência exclusiva e reclusa dessa pasta. 
- **Manutenção e Migrabilidade:** Se num futuro ocorrer a demanda de abandonar o Postgres com a substituição para outro banco de dados, **nenhuma** linha de código no seu `core` será afetada. Bastaria plugar um novo dataprovider compatível com as regras de saída para restabelecer os serviços. Se num momento vamos usar o Feign para consumir uma API externa fica aqui dentro.

## 5. `configuration` (Compositor Principal)
**Papel:** Orquestrar o Boot da aplicação, carregar instâncias e garantir todas as Injeções de Dependências.
Seguindo os preceitos do *Dependency Injection Container* em Clean Architecture, este é um módulo propositalmente "sujo", onde as cordas e conexões de cada ponta isolada são amarradas.
- **Responsabilidade:** Este local absorve e importa o Spring Boot massivo, Actuator para observabilidade, e injeta **todos** os demais módulos do projeto (`core`, `entrypoint`, `dataprovider`, etc).
- **Injeção:** O `configuration` é o cérebro físico ligando implementações providas no `dataprovider` à interfaces exigidas pelo `core`, deixando tudo preparado para uso, e as implementações providas no core à interfaces exigidas pelo `entrypoint`.

## 6. `commons` (Utilitários Compartilhados)
**Papel:** Guardar classes básicas e utilitárias (Enums corporativos, Formatadores, classes geradoras etc) de uso genérico e isentas de dependências complexas. Atendem transversalmente a necessidade de qualquer módulo sem violar a *The Dependency Rule* (Regra de Dependência em um único sentido na Clean Architecture).

---
### Resumo do Ciclo de Autonomia
1. Request HTTP bate no **`entrypoint`**.
2. **`entrypoint`** empacota a info e despacha para uma interface de entrada do **`boundary`**.
3. A interface possui uma lógica executável injetada, que roda dentro do **`core`** sem saber da existência do acesso REST.
4. Caso precise buscar no banco de dados, no meio da execução do **`core`**, aciona-se um método das interfaces de saída no **`boundary`**.
5. Sem que o **`core`** saiba que é Postgres, o serviço no **`dataprovider`** implementa a consulta e repassa a informação persistida de volta.
