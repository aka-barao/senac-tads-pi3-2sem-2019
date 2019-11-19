<%-- 
    Document   : ClienteBoot
    Created on : 19/11/2019, 17:05:10
    Author     : Gabrielle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link href="css/bootstrap.min.css" rel="stylesheet">
        <style>
            
            .container{
                position: relative;
                top:100px;
                
            }
            
        </style>
    </head>
    <body>
         <!-- MENU-->        
          <nav class="nav bg-dark">
                <a class="nav-link active" href="#">Ativo</a>
                <a class="nav-link" href="#">Link</a>
                <a class="nav-link" href="#">Link</a>
                <a class="nav-link disabled" href="#">Desativado</a>
            </nav>
        
        <!-- CONTAINER-->        
        <div class="container">
        
        <!-- H1-->        
            <h1 class="col-md-6 offset-md-3">Cadastro Cliente</h1>
        
        <!-- INICIO DO FORM-->        
            <form>
                
                
        <!-- COMEÇO / NOME E SOBRENOME -->       
            <div class="form-row">
                <div class="form-group col-md-6">
                        <label for="inputEmail4">Nome</label>
                        <input type="text" class="form-control" id="inputEmail4" placeholder="Nome">
                </div>
                    
                <div class="form-group col-md-6">
                        <label for="inputPassword4">Sobrenome</label>
                        <input type="text" class="form-control" id="inputPassword4" placeholder="Sobrenome">
                </div>
            </div>
        <!-- FIM / NOME E SOBRENOME -->    
             
        <!-- COMEÇO/ CPF E RG -->  
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="inputEmail4">CPF</label>
                    <input type="text" class="form-control" id="inputEmail4" placeholder="CPF">
                </div>
                    
                <div class="form-group col-md-6">
                    <label for="inputPassword4">RG</label>
                    <input type="text" class="form-control" id="inputPassword4" placeholder="RG">
                </div>
            </div>
        <!-- FIM / CPF E RG -->
             
        <!-- COMEÇO / DATA DE NASCIMENTO E SEXO--> 
            <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="txtDtNascimento">Data de nascimento</label>
                            <input type="date" class="form-control" name="dtNascimento">
                            <div class="erro-input">
                                Digite uma data válida.
                            </div>
                    </div>
             
                     <div class="form-group col-md-6">
                        <label for="exampleFormControlSelect1">Sexo</label>
                        <select class="form-control">
                            <option>Feminino</option>
                            <option>Masculino</option>
                        </select>
                    </div>
            </div>
        <!-- FIM / DATA DE NASCIMENTO E SEXO-->            
                
        <!-- COMEÇO / ENDERECO-->           
                    <div class="form-group">
                          <label for="inputAddress">Endereço</label>
                          <input type="text" class="form-control" placeholder="Ex. Rua Ipanema, nº 0">
                    </div>
        <!-- FIM / ENDERECO-->                
            
            
        <!-- COMEÇO / BAIRRO E CEP-->        
                <div class="form-row">
                    <div class="form-group col-md-6">
                          <label for="inputAddress2">Bairro</label>
                          <input type="text" class="form-control" placeholder="Ex. Jardim Veneza">
                    </div>
                    
                    <div class="form-group col-md-6">
                      <label for="inputAddress2">CEP</label>
                      <input type="text" class="form-control" placeholder="CEP">
                    </div>
                    
                </div>
        <!-- FIM / BAIRRO E CEP-->     
            
            
        <!-- COMEÇO /  CIDADE, ESTADO E CEP-->            
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputCity">Cidade</label>
                        <input type="text" class="form-control" placeholder="Ex. São Paulo">
                    </div>
                    
                    <div class="form-group col-md-4">
                        <label for="inputCity">Estado</label>
                        <input type="text" class="form-control" placeholder="Ex. SP">
                    </div>
                    
                    <div class="form-group col-md-2">
                        <label for="inputCEP">CEP</label>
                        <input type="text" class="form-control">
                    </div>
                </div>
        <!-- FIM / CIDADE, ESTADO E CEP-->    

        <!-- BOTOES /  INSERIR E FECHAR-->        
                    <button type="submit" class="btn btn-primary">Inserir</button>
                    <button type="button" class="btn btn-primary">Fechar</button>
            </form>
            
            
        </div>
    </body>
</html>
