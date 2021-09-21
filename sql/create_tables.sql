CREATE TABLE usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    login VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_nascimento DATE,
    celular VARCHAR(11),
    ativo BOOL NOT NULL,
    
    PRIMARY KEY (id_usuario)
);

CREATE TABLE permissao (
    id_permissao INT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL,

    PRIMARY KEY (id_permissao)
);

CREATE TABLE usuario_permissao (
    id_usuario_permissao INT NOT NULL AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_permissao INT NOT NULL,
    
    PRIMARY KEY (id_usuario_permissao),
    
    FOREIGN KEY (id_usuario)
    	REFERENCES usuario(id_usuario),
    
    FOREIGN KEY (id_permissao)
    	REFERENCES permissao(id_permissao)
);

CREATE TABLE produto (
    id_produto INT NOT NULL AUTO_INCREMENT,
    quantidade INT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    preco DOUBLE NOT NULL,
    tipo VARCHAR(255),
    
    PRIMARY KEY (id_produto)
);

CREATE TABLE fornecedor (
	id_fornecedor INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(12),
    cnpj VARCHAR(14) NOT NULL,
    endereco VARCHAR(255),
    data_cadastro DATE NOT NULL,
    
    PRIMARY KEY (id_fornecedor)
);

INSERT INTO permissao (descricao) VALUES ("ROLE_ADMINISTRADOR"), ("ROLE_USUARIO"), ("ROLE_USUARIO_VIP");