CREATE TABLE cliente(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	rg BIGINT,
	telefone BIGINT
);

CREATE TABLE tipo_servico(
        id SERIAL PRIMARY KEY,
	numero INTEGER,
	nome VARCHAR(30) NOT NULL,
	tipo_atendimento VARCHAR(20) NOT NULL,
	preco DECIMAL
);

CREATE TABLE pet(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(20) NOT NULL,
	tipo_animal VARCHAR(20) NOT NULL,
	cliente INTEGER REFERENCES CLIENTE(id)
);

CREATE TABLE venda(
	id SERIAL PRIMARY KEY,
	data_hora TIMESTAMP NOT NULL,
	valor_total DECIMAL 
);

CREATE TABLE item_venda(
	id SERIAL PRIMARY KEY,
	venda INTEGER REFERENCES venda(id),
	pet INTEGER REFERENCES pet(id),
	tipo_servico INTEGER REFERENCES tipo_servico(id)
);