<%-- 
    Document   : Login
    Created on : 19/11/2019, 23:16:57
    Author     : Gabrielle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
         <link href="vendor/Css/bootstrap.min.css" rel="stylesheet">
        <link href="vendor/Css/simple-sidebar.css" rel="stylesheet">
        <style>
            
            .container{
                position: relative;
                top:100px;
                
            }
            
        </style>
    </head>
    <body>
         <div class="container">
            
            <h1 class="col-md-4 offset-md-3">Login</h1>
            
            <form class="form-horizontal" >
                <input type="hidden" name="acao" value="LOGAR"/>
                
                <div class="form-group col-md-4 offset-md-3">
                <input class="form-control" type="email" name="email" id="txtEmail"  placeholder="Email">
                </div>
                
                <div class="form-group col-md-4 offset-md-3">
                <input class="form-control" type="password" name="senha" id="txtSenha"  placeholder="Senha">
                </div>
                
                <div class="col-md-6 offset-md-3">
                <button type="submit" class="btn btn-primary" name="btnEntrar" id="btnConfirmar"> Entrar </button> 
                </div>
                
                
             </form>
        </div>
    </body>
</html>
