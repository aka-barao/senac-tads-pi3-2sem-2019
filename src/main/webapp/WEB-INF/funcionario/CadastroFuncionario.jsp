<%-- 
    Document   : CadastroFuncionario
    Created on : 19/11/2019, 23:10:21
    Author     : Gabrielle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Funcionario</title>
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/simple-sidebar.css" rel="stylesheet">
        
        <style>
            
            .container{
                position: relative;
                top:100px;
                
            }
            
        </style>
    </head>
    <body>
        <!-- MENU--> 
        <div class="d-flex" id="wrapper">
        
         <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">Scorpions </div>
      <div class="list-group list-group-flush">
        <a href="#" class="list-group-item list-group-item-action bg-light">Home</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">Venda</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">Cliente</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">Funcionário</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">Relatório</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">Produto</a>
      </div>
      
      <br>
      <h5>&nbspFuncionário</h5>
      <div class="list-group list-group-flush">
        <a href="#" class="list-group-item list-group-item-action bg-light">Cadastrar Funcionário</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">Funcionário Cadastrado</a>
     
      </div>
    </div>
    <!-- /#sidebar-wrapper -->
        
        
    <div id="page-content-wrapper">         
        
        <nav class="nav bg-light justify-content-end">
          
          <a class="nav-link" href="#">Sair</a>
        </nav>
    <!-- CONTAINER-->      
        <div class="container">
    
    <!-- H1-->         
            <h1>Cadastro Funcionário</h1>
    
   <!-- INICIO DO FORM-->  
            <form>
    

    <!-- COMEÇO / NOME E SOBRENOME -->                  
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="inputEmail4">Nome</label>
                    <input type="text" class="form-control" placeholder="Nome">
                </div>
                
                
                <div class="form-group col-md-6">
                    <label for="inputPassword4">Sobrenome</label>
                    <input type="text" class="form-control" placeholder="Sobrenome">
                </div>
            </div>
     <!-- FIM / NOME E SOBRENOME -->
     
     
     <!-- COMEÇO/ CPF E RG -->         
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputEmail4">CPF</label>
                        <input type="text" class="form-control"  placeholder="CPF">
                </div>
                
                <div class="form-group col-md-6">
                    <label for="inputPassword4">RG</label>
                    <input type="text" class="form-control" placeholder="RG">
                </div>
            </div>
     <!-- FIM / CPF E RG -->
       
       
    <!-- COMEÇO / DATA DE NASCIMENTO E SEXO-->        
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="txtDtNascimento">Data de nascimento</label>
                
                <div>
                    <input type="date" class="form-control" name="dtNascimento">
                        <div class="erro-input">
                            Digite uma data válida.
                        </div>
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
               
            <div class="form-row">
                
                <div class="form-group col-md-6">
                    <label for="inputAddress">Login</label>
                    <input type="text" class="form-control" placeholder="Login">
                </div>
                
                <div class="form-group col-md-6">
                    <label for="inputAddress">Senha</label>
                    <input type="text" class="form-control" placeholder="Senha">
                </div>
                   
            </div>   
   
    
    <!-- COMEÇO / ENDERECO-->             
                <div class="form-group">
                    <label for="inputAddress">Endereço</label>
                    <input type="text" class="form-control" placeholder="Ex. Rua Ipanema, nº 0">
                </div>
                    
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
   
   
   <br><br>
   
          <table class="table">
          <thead class="thead-dark">
            <tr>
              <th scope="col">#</th>
              <th scope="col">Primeiro</th>
              <th scope="col">Último</th>
              <th scope="col">Nickname</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>@mdo</td>
            </tr>
            <tr>
              <th scope="row">2</th>
              <td>Jacob</td>
              <td>Thornton</td>
              <td>@fat</td>
            </tr>
            <tr>
              <th scope="row">3</th>
              <td>Larry</td>
              <td>the Bird</td>
              <td>@twitter</td>
            </tr>
          </tbody>
        </table> 
            
        </div>
     <!-- container -->   
      
    </div>
    <!-- /#page-content-wrapper -->
  </div>
  <!-- /#wrapper -->
  
  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Menu Toggle Script -->
  <script>
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>
    </body>
</html>
