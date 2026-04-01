Funcionalidade: Sistema de Gestao de Livros, Autores e Assuntos

Contexto: O sistema atende aos testes tecnicos onde e necessario gerenciar 3 pilares principais: Autor, Assunto e Livro. Incluindo a vinculacao entre eles e tratativas rigorosas de erros.

#---------------------------------------
# CRUD DE AUTOR
#---------------------------------------
Cenario: Inclusao de um novo autor
Dado que o usuario preenche o nome do autor com "George R. R. Martin"
Quando ele solicita a gravacao do cadastro
Entao o sistema deve persistir os dados na base e retornar sucesso

Cenario: Edicao de autor
Dado que exista o autor "George R. R. Martin" previamente na base
Quando o usuario altera seu nome para "George Raymond Richard Martin"
E submete a alteracao
Entao o sistema deve atualizar os dados de forma correta e exibir mensagem de sucesso

Cenario: Exclusao de autor (sem violacao de chave estrangeira)
Dado que um autor especifico nao esteja vinculado a nenhum livro
Quando o usuario deseja exclui-lo
Entao o sistema deve apagar o registro com sucesso

#---------------------------------------
# CRUD DE ASSUNTO
#---------------------------------------
Cenario: Inclusao de novo assunto
Dado que o usuario cadastre o assunto de descricao "Ficcao Cientifica"
Quando os dados sao enviados
Entao o sistema armazena a descricao e o codigo com integridade

#---------------------------------------
# CRUD DE LIVRO (com relacionamentos e valor R$)
#---------------------------------------
Cenario: Criacao de livro simples vinculado a autores e assuntos
Dado que ja temos os assuntos "Fantasia" e o autor "J. R. R. Tolkien" criados
E o usuario informa os dados Titulo, Editora, Edicao, Ano de Publicacao
E o campo Valor e preenchido
Quando e acionado o comando de salvar livro
Entao a aplicacao deve incluir os dados na tabela de livros
E inserir as FKs apontando para Assunto e Autor na suas respectivas tabelas Many-ToMany

Cenario: Validacao de formatacao (Valor em Reais R$)
Dado que a tela possui o campo para valor monetario do livro
Quando o usuario informa o valor
Entao a aplicacao deve ter mascaras provendo a validacao e persistir de forma estruturada no banco (ex: BigDecimal na camada backend)

#---------------------------------------
# RELATORIO
#---------------------------------------
Cenario: Relatorio agrupado via View de banco de dados
Dado que existem dados multiplos compondo o ecosistema de testes
Quando o usuario acessar e acionar a construcao do Relatorio
Entao os dados lidos devem provir de uma View SQL ja pre-compilada
E serao apresentados agrupados prioritariamente pelo campo de Autor (suportando condicao de um livro com multiplos autores)

#---------------------------------------
# TRATAMENTOS DE EXCECAO E ARQUITETURA (TDD)
#---------------------------------------
Cenario: Erro na camada de integracao com banco caindo nos handlers customizados
Dado que num momento em operacao o banco perca conectividade (Timeout/Exception SQLError)
Quando os fluxos de persistencia (use cases) executarem a chamada a porta de saida
Entao o sistema deve capturar as excecoes
E traduzi-las ou retorna-las de forma suave e legivel pro front-end (ex: "Falha de comunicacao ao tentar persistir"), desprezando os temidos Try/Catchs genericos de Exception base.
