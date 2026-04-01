CREATE TABLE Livro (
    Codl UUID PRIMARY KEY,
    Titulo VARCHAR(40),
    Editora VARCHAR(40),
    Edicao INTEGER,
    AnoPublicacao VARCHAR(4),
    Valor NUMERIC(10,2)
);

CREATE TABLE Autor (
    CodAu UUID PRIMARY KEY,
    Nome VARCHAR(40)
);

CREATE TABLE Assunto (
    codAs UUID PRIMARY KEY,
    Descricao VARCHAR(20)
);

CREATE TABLE Livro_Autor (
    Livro_Codl UUID,
    Autor_CodAu UUID,
    PRIMARY KEY (Livro_Codl, Autor_CodAu),
    FOREIGN KEY (Livro_Codl) REFERENCES Livro(Codl),
    FOREIGN KEY (Autor_CodAu) REFERENCES Autor(CodAu)
);

CREATE INDEX Livro_Autor_FKIndex1 ON Livro_Autor (Livro_Codl);
CREATE INDEX Livro_Autor_FKIndex2 ON Livro_Autor (Autor_CodAu);

CREATE TABLE Livro_Assunto (
    Livro_Codl UUID,
    Assunto_codAs UUID,
    PRIMARY KEY (Livro_Codl, Assunto_codAs),
    FOREIGN KEY (Livro_Codl) REFERENCES Livro(Codl),
    FOREIGN KEY (Assunto_codAs) REFERENCES Assunto(codAs)
);

CREATE INDEX Livro_Assunto_FKIndex1 ON Livro_Assunto (Livro_Codl);
CREATE INDEX Livro_Assunto_FKIndex2 ON Livro_Assunto (Assunto_codAs);

CREATE VIEW vw_relatorio_livros_autores AS
SELECT 
    l.Codl as livro_id,
    l.Titulo as titulo,
    l.Editora as editora,
    l.Edicao as edicao,
    l.AnoPublicacao as ano_publicacao,
    au.Nome as autor_nome,
    assu.Descricao as assunto_descricao
FROM Livro l
LEFT JOIN Livro_Autor la ON l.Codl = la.Livro_Codl
LEFT JOIN Autor au ON la.Autor_CodAu = au.CodAu
LEFT JOIN Livro_Assunto lsub ON l.Codl = lsub.Livro_Codl
LEFT JOIN Assunto assu ON lsub.Assunto_codAs = assu.codAs;

CREATE TABLE Usuario (
    id UUID PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Inserindo um usuario Admin default. Password formatado com BCrypt para a string 'admin'
INSERT INTO Usuario (id, username, password, role) 
VALUES ('123e4567-e89b-12d3-a456-426614174000', 'admin', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5O', 'ADMIN');
