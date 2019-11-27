-- Cadastro de massa de registros na Base de dados

USE empresa_tades_v2;

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
INSERT INTO cliente(nome, data_nascimento, cpf)
VALUES ('Clara Marlene Duarte', '1942-03-16', '35699154175');
INSERT INTO cliente(nome, data_nascimento, cpf)
VALUES ('Matheus Thomas Dias', '1950-02-01', '82326424409');
INSERT INTO cliente(nome, data_nascimento, cpf)
VALUES ('Nicolas Iago Barros', '1965-05-02', '57707838777');
INSERT INTO funcionario(id_cargo, id_departamento, id_unidade_empresa, nome, data_nascimento, cpf)
VALUES (1,1,2,'Rodrigo Kaique Yago Silva', '1999-12-22', '40362629048');
INSERT INTO funcionario(id_cargo, id_departamento, id_unidade_empresa, nome, data_nascimento, cpf)
VALUES (2,2,3,'Sophia Ester das Neves', '1975-08-04', '95041208492');
INSERT INTO funcionario(id_cargo, id_departamento, id_unidade_empresa, nome, data_nascimento, cpf)
VALUES (3,3,4,'Laís Allana Duarte', '1965-07-14', '74169428889');
INSERT INTO funcionario(id_cargo, id_departamento, id_unidade_empresa, nome, data_nascimento, cpf)
VALUES (4,4,1,'Valentina Yasmin Mariah Baptista', '1996-06-10', '64094257918');

-- Cargos
INSERT INTO cargo(descricao)
VALUES ('Vendedor');
INSERT INTO cargo(descricao)
VALUES ('Analista de TI');
INSERT INTO cargo(descricao)
VALUES ('Gerente Regional');
INSERT INTO cargo(descricao)
VALUES ('Diretor');

-- Departamentos
INSERT INTO departamento(descricao)
VALUES ('Vendas');
INSERT INTO departamento(descricao)
VALUES ('TI');
INSERT INTO departamento(descricao)
VALUES ('Marketing');
INSERT INTO departamento(descricao)
VALUES ('Administrativo');
