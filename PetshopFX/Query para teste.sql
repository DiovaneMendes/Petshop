CREATE TABLE pet(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(20) NOT NULL,
	tipo_animal VARCHAR(20) NOT NULL,
	cliente INTEGER REFERENCES CLIENTE(id)
);


INSERT INTO pet(nome, tipo_animal, cliente) VALUES('mimita', 'gato', 10);

SELECT 
	pet.id, 
	pet.nome, 
	pet.tipo_animal, 
	cliente.nome 
FROM 
	pet 
JOIN 
	cliente ON(pet.cliente = cliente.id);

DELETE FROM pet WHERE id = 10;

