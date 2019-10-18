<%-- 
    Document   : cadastroProduto
    Created on : 17/10/2019, 21:58:23
    Author     : gabrielle.csilva11
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Cadastro de Produtos</title>
    </head>
    <style type="text/css">
        body{
            background-color: #DCDCDC;
        }
        .erro-input {
            display: none;
            color: #e74c3c;
        }
        #logoTades{
            width: 80px;
            height: 60px; 
            margin: 0 50px 0 24px;
            float: left;
            font-size: 14px;
            position: relative;
        }
        header{
            margin: 0;
            background-color: black;
        }
        .Menu ul{
            margin: 0;
            padding: 0;
            width: 100%;
            border-radius: 5px;
            list-style: none;
            align-content: center;
        }
        .Menu li{
            text-align: center;
            display: inline;
            font: 15px arial;
        }
        .Menu li a{
            color: #888;
            text-decoration: none;
            padding: 20px;
            display: inline-block;
        }
        #Login{
            float: right;
        }
        .Container_Form{
            position: absolute;
            margin-left: 40%;
            margin-right: 50%;
            margin-top: 10%;
        }
        .campo{
            width: 350px;
            padding: 5px;
            margin-bottom: 3px;
            border-color: red;
            border-radius: 5px;
        }
    </style>
    <body>
        <header class="Menu">
            <a class="logo" href="Index.html">
                <img id="logoTades" src="Imagens/logo.png" alt="Logo da Empresa Tades">
            </a>
            <ul class="MenuLinks">
                <li id="Login"><a href="Login.html">Login</a></li>
            </ul>
        </header>
        <div class ="Container_Form">
            <form class="form" method="POST">
                <div>
                    <label for="txtDescricao">Descrição</label>
                    <div>
                        <input class="campo" type="text" name="descricao" id="txtDesc" placeholder="Descrição" required maxlength="100"/>
                        <div class="erro-input">
                            Digite a descrição
                        </div>
                    </div>
                </div>
                <div>
                    <label for="txtPreço">Preço</label>
                    <div>
                        <input class="campo" type="text" name="preco" id="txtPreco" placeholder="Preço" required maxlength="100"/>
                        <div class="erro-input">
                            Digite o preço do produto
                        </div>
                    </div>
                </div>
                <div>
                    <label for="txtQuantidade">Quantidade</label>
                    <div>
                        <input class="campo" type="text" name="Quantidade" id="txtQuant" placeholder="Quantidade"/>
                        <div class="erro-input">
                            Digite a quantidade do produto.
                        </div>
                    </div>
                </div>
                <div>
                    <label for="txtCategoria">Categoria</label>
                    <div>
                        <input class="campo" type="text" name="Categoria" id="txtCategoria" placeholder="Categoria"/>
                        <div class="erro-input">
                            Digite a categoria do produto.
                        </div>
                    </div>
                </div>
                <div>
                    <label for="txtDtCadastro">DataCadastro</label>
                    <div>
                        <input class="campo" type="date" name="dataCadastro" id="txtDtCadastro"/>
                    </div>
                </div>
                <fieldset>
                    <legend>Unidade</legend>
                    <div>
                        <div>
                            <input type="checkbox" name="unidade" 
                                   id="unidad1" value="unidade1" />
                            <label for="unidade1">
                                Unidade 1
                            </label>
                        </div>
                        <div>
                            <input type="checkbox" name="interesses" 
                                   id="unidade2" value="unidade2" />
                            <label for="unidade2">
                                Unidade 2
                            </label>
                        </div>
                        <div>
                            <input type="checkbox" name="interesses" 
                                   id="Unidade3" value="unidade3" />
                            <label for="Unidade3">
                                Unidade 3
                            </label>
                        </div>
                        <div>
                            <input type="checkbox" name="interesses" 
                                   id="Unidade4" value="unidade4" />
                            <label for="Unidade4">
                                Unidade 4
                            </label>
                        </div>
                        <div class="erro-input">Selecione pelo menos uma opção</div>

                        <button type="submit">Enviar</button>
                        <button type="reset">Resetar dados</button>
                    </div>
                </fieldset>
            </form>
        </div>
    </body>
</html>
