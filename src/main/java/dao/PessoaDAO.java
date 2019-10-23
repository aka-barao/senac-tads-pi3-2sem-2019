/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;
import model.Funcionario;
import model.Pessoa;

/**
 *
 * @author vinicius
 */
public class PessoaDAO {

    private PreparedStatement instrucao;
    private ArrayList<Pessoa> listaDePessoas;

    public PessoaDAO() { // Avisa no console caso o programa consiga se conectar sem problemas ao BD;
        try {
            Connection testaConexao = new ConnectionFactory().getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            testaConexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados!");
        }

    }

    public boolean cadastrarNovaPessoa(Pessoa pessoa) {
        boolean retorno = false;

        String insereProdutoSQL = "INSERT INTO pessoa(nome, data_nascimento, cpf)"
                + " VALUES (?,?,?)";

        // try-with-resources || Conexão será aberta novamente dentro do "try" e fechada automaticamente ao final dele.
        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(insereProdutoSQL);

            instrucao.setString(1, pessoa.getNome());
            instrucao.setDate(2, new java.sql.Date(pessoa.getDataNascimento().getTime()));
            instrucao.setString(3, pessoa.getCpf());

            instrucao.execute();
            instrucao.close();

            retorno = true;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Cadastro de Pessoa!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }
    }
    
    public boolean cadastrarNovoFuncionario(Funcionario funcionario){
        boolean retorno = false;
        
        cadastrarNovaPessoa(funcionario);
        Pessoa pessoaFuncionario = buscarPessoaPorCPF(funcionario.getCpf());

        String cadastraFuncionarioSQL = "INSERT INTO funcionario(id_pessoa, id_cargo, id_departamento, id_unidade_empresa)"
                + " VALUES (?,?,?,?)";

        // try-with-resources || Conexão será aberta novamente dentro do "try" e fechada automaticamente ao final dele.
        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(cadastraFuncionarioSQL);

            instrucao.setInt(1, pessoaFuncionario.getIdPessoa());
            instrucao.setInt(2, funcionario.getCargo().getIdCargo());
            instrucao.setInt(3, funcionario.getDepartamento().getIdDepartamento());
            instrucao.setInt(4, funcionario.getUnidadeEmpresa().getIdUnidadeEmpresa());

            instrucao.execute();
            instrucao.close();

            retorno = true;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Cadastro de Funcionário!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }
    }
    /*
    public boolean cadastrarNovoCliente(Cliente cliente){
        boolean retorno = false;
        
        cadastrarNovaPessoa(cliente);
        Pessoa pessoaCliente = buscarPessoaPorCPF(cliente.getCpf());

        String cadastraClienteSQL = "INSERT INTO cliente(id_pessoa)"
                + " VALUES (?)";

        // try-with-resources || Conexão será aberta novamente dentro do "try" e fechada automaticamente ao final dele.
        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(cadastraClienteSQL);

            instrucao.setInt(1, pessoaCliente.getIdPessoa());

            instrucao.execute();
            instrucao.close();

            retorno = true;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Cadastro de Cliente!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }
    }
    */
    public ArrayList<Pessoa> listarPessoas() {
        String codigoSQL
                = "SELECT "
                + "pessoa.id_pessoa,"
                + "pessoa.nome,"
                + "pessoa.data_nascimento,"
                + "pessoa.cpf,"
                + "cliente.id_cliente,"
                + "funcionario.id_funcionario "
                + "FROM pessoa "
                + "LEFT JOIN cliente ON "
                + "cliente.id_pessoa = pessoa.id_pessoa "
                + "LEFT JOIN funcionario ON "
                + "funcionario.id_pessoa = pessoa.id_pessoa";

        listaDePessoas = new ArrayList<Pessoa>();

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            ResultSet resultado = instrucao.executeQuery();

            while (resultado.next()) {

                if (resultado.getInt("cliente.id_cliente") != 0) {
                    Cliente cliente = instanciarCliente(resultado);
                    listaDePessoas.add(cliente);
                }

                if (resultado.getInt("funcionario.id_funcionario") != 0) {
                    Funcionario funcionario = instanciarFuncionario(resultado);
                    listaDePessoas.add(funcionario);
                }

            }

            resultado.close();
            instrucao.close();
            conexao.close();

            return listaDePessoas;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Consulta de Pessoas!");
            throw new RuntimeException(e);
        }
    }
    
    private Pessoa instanciarPessoa(ResultSet resultadoConsulta) throws SQLException {
        Pessoa pessoa = new Pessoa() {};

        pessoa.setIdPessoa(resultadoConsulta.getInt("pessoa.id_pessoa"));
        pessoa.setNome(resultadoConsulta.getString("pessoa.nome"));
        pessoa.setDataNascimento(new java.util.Date(resultadoConsulta.getTimestamp("pessoa.data_nascimento").getTime()));
        pessoa.setCpf(resultadoConsulta.getString("pessoa.cpf"));

        return pessoa;
    }

    private Cliente instanciarCliente(ResultSet resultadoConsulta) throws SQLException {
        Cliente cliente = new Cliente(
                resultadoConsulta.getInt("cliente.id_cliente"),
                resultadoConsulta.getInt("pessoa.id_pessoa"),
                resultadoConsulta.getString("pessoa.nome"),
                new java.util.Date(resultadoConsulta.getTimestamp("pessoa.data_nascimento").getTime()),
                resultadoConsulta.getString("pessoa.cpf")
        );

        return cliente;
    }

    private Funcionario instanciarFuncionario(ResultSet resultadoConsulta) throws SQLException {
        Funcionario funcionario = new Funcionario();

        funcionario.setIdPessoa(resultadoConsulta.getInt("pessoa.id_pessoa"));
        funcionario.setIdFuncionario(resultadoConsulta.getInt("funcionario.id_funcionario"));
        funcionario.setNome(resultadoConsulta.getString("pessoa.nome"));
        funcionario.setDataNascimento(new java.util.Date(resultadoConsulta.getTimestamp("pessoa.data_nascimento").getTime()));
        funcionario.setCpf(resultadoConsulta.getString("pessoa.cpf"));

        // Falta implementar
        // setCargo
        // setDepartamento
        // setUnidadeEmpresa
        return funcionario;
    }
    
    public Pessoa buscarPessoaPorCPF(String cpf){
        String codigoSQL
                = "SELECT "
                + "id_pessoa,"
                + "nome,"
                + "data_nascimento,"
                + "cpf "
                + "FROM pessoa "
                + "WHERE cpf = ?";
        
        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            instrucao.setString(1, cpf);
            ResultSet resultado = instrucao.executeQuery();

            Pessoa pessoa = instanciarPessoa(resultado);

            resultado.close();
            instrucao.close();
            conexao.close();

            return pessoa;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Busca de Pessoa!");
            throw new RuntimeException(e);
        }
    }
    
    public Cliente buscarClientePorCPF(String cpf){
        String codigoSQL
                = "SELECT "
                + "id_cliente,"
                + "nome,"
                + "data_nascimento,"
                + "cpf "
                + "FROM Cliente "
                + "WHERE cpf = ?";
        
        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            instrucao.setString(1, cpf);
            ResultSet resultado = instrucao.executeQuery();

            Cliente cliente = instanciarCliente(resultado);

            resultado.close();
            instrucao.close();
            conexao.close();

            return cliente;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Busca de Pessoa!");
            throw new RuntimeException(e);
        }
    }
    
    public Funcionario buscarFuncionarioPorCPF(String cpf){
        String codigoSQL
                = "SELECT "
                + "id_funcionario,"
                + "nome,"
                + "data_nascimento,"
                + "cpf "
                + "FROM Funcionario "
                + "WHERE cpf = ?";
        
        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            instrucao.setString(1, cpf);
            ResultSet resultado = instrucao.executeQuery();

            Funcionario funcionario = instanciarFuncionario(resultado);

            resultado.close();
            instrucao.close();
            conexao.close();

            return funcionario;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Busca de Pessoa!");
            throw new RuntimeException(e);
        }
    }
    
    public Cliente buscarClientePorID(int ID){
        String codigoSQL
                = "SELECT "
                + "id_cliente,"
                + "nome,"
                + "data_nascimento,"
                + "cpf "
                + "FROM Cliente "
                + "WHERE id = ?";
        
        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            instrucao.setInt(1, ID);
            ResultSet resultado = instrucao.executeQuery();

            Cliente cliente = instanciarCliente(resultado);

            resultado.close();
            instrucao.close();
            conexao.close();

            return cliente;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Busca de Pessoa!");
            throw new RuntimeException(e);
        }
    }
    
    
    
}
