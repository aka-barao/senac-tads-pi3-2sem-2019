-- Cadastro de massa de registros na Base de dados

USE empresa_tades;

-- Categoria de Produtos
INSERT INTO categoria_produto(descricao) VALUES ('Monitor');
INSERT INTO categoria_produto(descricao) VALUES ('Smartphones');
INSERT INTO categoria_produto(descricao) VALUES ('Audio');
INSERT INTO categoria_produto(descricao) VALUES ('Notebooks');
INSERT INTO categoria_produto(descricao) VALUES ('Computadores');
INSERT INTO categoria_produto(descricao) VALUES ('Mouse');
INSERT INTO categoria_produto(descricao) VALUES ('Teclados');

-- Produtos
INSERT INTO produto(nome, descricao, valor, id_categoria_produto, data_cadastro)
VALUES ('Motorola One Vision',
'Azul Safira, com Tela de 6,3”, 4G, 128 GB e Câmera Dupla de 48MP + 5MP - XT1970-1',
1415.00,
2,
CURRENT_TIMESTAMP());
INSERT INTO produto(nome, descricao, valor, id_categoria_produto, data_cadastro)
VALUES ('Monitor Curved 24"',
'Samsung Full HD com 3000:1 de Contraste',
869.72,
1,
CURRENT_TIMESTAMP());
INSERT INTO produto(nome, descricao, valor, id_categoria_produto, data_cadastro)
VALUES ('Fone de Ouvido JBL',
'On Ear Preto - C300SI',
87.27,
3,
CURRENT_TIMESTAMP());
INSERT INTO produto(nome, descricao, valor, id_categoria_produto, data_cadastro)
VALUES ('Notebook Dell',
'Intel® Core™ i7 8565U, 16GB, 1TB + 128 SSD, 14", NVIDIA® GeForce® MX150 GDDR5 2GB, Bordô - i14-5480-A40X',
6005.56,
4,
CURRENT_TIMESTAMP());
INSERT INTO produto(nome, descricao, valor, id_categoria_produto, data_cadastro)
VALUES ('All in One Dell',
'Intel Core i7 7500U, 12GB, 1TB, Tela de 23,8 - iOne-3477-A40',
5968.02,
5,
CURRENT_TIMESTAMP());
INSERT INTO produto(nome, descricao, valor, id_categoria_produto, data_cadastro)
VALUES ('Mouse Gamer Hyperx',
'Pulsefire Surge RGB 16000 DPI Preto Com Cinza',
350.53,
6,
CURRENT_TIMESTAMP());
INSERT INTO produto(nome, descricao, valor, id_categoria_produto, data_cadastro)
VALUES ('Teclado Gamer Razer ',
'Blackwidow Tournament LGD Gaming White Special Edition para PC e Mac',
745.00,
7,
CURRENT_TIMESTAMP());

-- Unidades Empresa
INSERT INTO unidade_empresa(descricao, tipo_unidade)
VALUES ('Matriz São Paulo - SP', 1); -- Tipo Unidade 1 = Matriz
INSERT INTO unidade_empresa(descricao, tipo_unidade)
VALUES ('Filial Joinville - SC', 2); -- Tipo Unidade 2 = Filial
INSERT INTO unidade_empresa(descricao, tipo_unidade)
VALUES ('Filial Brasília - DF', 2);
INSERT INTO unidade_empresa(descricao, tipo_unidade)
VALUES ('Filial Campina Grande - PB', 2);
INSERT INTO unidade_empresa(descricao, tipo_unidade)
VALUES ('Filial Belo Horizonte - MG', 2);
INSERT INTO unidade_empresa(descricao, tipo_unidade)
VALUES ('Filial Recife - PE', 2);

-- Quantidade Estoque
INSERT INTO quantidade_estoque(id_produto, id_unidade_empresa, quantidade_estoque)
VALUES (1, 1, 42);
INSERT INTO quantidade_estoque(id_produto, id_unidade_empresa, quantidade_estoque)
VALUES (4, 3, 24);
INSERT INTO quantidade_estoque(id_produto, id_unidade_empresa, quantidade_estoque)
VALUES (3, 2, 984);
INSERT INTO quantidade_estoque(id_produto, id_unidade_empresa, quantidade_estoque)
VALUES (2, 4, 7);
INSERT INTO quantidade_estoque(id_produto, id_unidade_empresa, quantidade_estoque)
VALUES (5, 1, 789456);
INSERT INTO quantidade_estoque(id_produto, id_unidade_empresa, quantidade_estoque)
VALUES (7, 1, 99);
INSERT INTO quantidade_estoque(id_produto, id_unidade_empresa, quantidade_estoque)
VALUES (6, 2, 1036);

-- Pessoas (Dados gerados em: https://www.4devs.com.br/gerador_de_pessoas)
INSERT INTO pessoa(nome, data_nascimento, cpf)
VALUES ('Clara Marlene Duarte', '16-03-1942', '35699154175');
INSERT INTO pessoa(nome, data_nascimento, cpf)
VALUES ('Matheus Thomas Dias', '02-01-1950', '82326424409');
INSERT INTO pessoa(nome, data_nascimento, cpf)
VALUES ('Nicolas Iago Barros', '05-02-1965', '57707838777');
INSERT INTO pessoa(nome, data_nascimento, cpf)
VALUES ('Rodrigo Kaique Yago Silva', '22-12-1999', '40362629048');
INSERT INTO pessoa(nome, data_nascimento, cpf)
VALUES ('Sophia Ester das Neves', '08-04-1975', '95041208492');
INSERT INTO pessoa(nome, data_nascimento, cpf)
VALUES ('Laís Allana Duarte', '14-07-1965', '74169428889');
INSERT INTO pessoa(nome, data_nascimento, cpf)
VALUES ('Valentina Yasmin Mariah Baptista', '06-10-1996', '64094257918');