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
