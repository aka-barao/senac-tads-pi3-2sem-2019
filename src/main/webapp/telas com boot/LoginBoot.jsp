<%-- 
    Document   : LoginBoot
    Created on : 19/11/2019, 17:07:05
    Author     : Gabrielle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="Css/bootstrap.min.css" rel="stylesheet">
        <style>
            
            .container{
                position: relative;
                top:100px;
            }
            
        </style>
    </head>
    <body>
        <nav class="nav bg-dark">
                <a class="nav-link active" href="#">Ativo</a>
                <a class="nav-link" href="#">Link</a>
                <a class="nav-link" href="#">Link</a>
                <a class="nav-link disabled" href="#">Desativado</a>
            </nav>
        
        <div class="container">
            
            <h1 class="col-md-6 offset-md-3">Login</h1>
            
            <form class="form-horizontal">
                
                <div class="form-group col-md-6 offset-md-3">
                <input class="form-control" type="email" name="email" id="txtEmail"  placeholder="Email">
                </div>
                
                <div class="form-group col-md-6 offset-md-3">
                <input class="form-control" type="password" name="senha" id="txtSenha"  placeholder="Senha">
                </div>
                
                <div class="col-md-6 offset-md-3">
                <button type="submit" class="btn btn-primary" name="btnEntrar" id="btnConfirmar"> Entrar </button> 
                </div>
                
                
             </form>
        </div>
    </body>
</html>
