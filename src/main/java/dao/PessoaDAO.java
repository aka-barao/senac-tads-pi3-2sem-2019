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
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    private ArrayList<Cliente> listaDeClientes;

    public PessoaDAO() { // Avisa no console caso o programa consiga se conectar sem problemas ao BD;
        try {
            Connection testaConexao = new ConnectionFactory().getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            testaConexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados!");
        }

    }

    public boolean cadastrarNovaPessoa(Cliente cliente) {
        boolean retorno = false;

        String insereProdutoSQL = "INSERT INTO pessoa(nome, data_nascimento, cpf)"
                + " VALUES (?,?,?)";

        // try-with-resources || Conexão será aberta novamente dentro do "try" e fechada automaticamente ao final dele.
        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(insereProdutoSQL);

            instrucao.setString(1, cliente.getNome());
            //instrucao.setDate(2, new java.sql.Date(cliente.getDataNascimento().getTime())); 
            SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
            Date datanasc = cliente.getDataNascimento();
            date.format(datanasc);
            instrucao.setDate(2, (java.sql.Date)datanasc);
            instrucao.setString(3, cliente.getCpf());

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
        
        //if(!cadastrarNovaPessoa(funcionario)) return retorno;
        
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
    
    public boolean cadastrarNovoCliente(Cliente cliente){
        boolean retorno = false;
        
        if(!cadastrarNovaPessoa(cliente)) return retorno;
        
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
    
    public ArrayList<Cliente> listarPessoas() {
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

        listaDeClientes = new ArrayList<>();

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            ResultSet resultado = instrucao.executeQuery();

            while (resultado.next()) {
                if (resultado.getInt("cliente.id_cliente") != 0) {
                    Cliente cliente = instanciarCliente(resultado);
                    listaDeClientes.add(cliente);
                }

                if (resultado.getInt("funcionario.id_funcionario") != 0) {
                    Funcionario funcionario = instanciarFuncionario(resultado);
                    listaDePessoas.add(funcionario);
                }

            }

            resultado.close();
            instrucao.close();
            conexao.close();
            
            return listaDeClientes;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Consulta de Pessoas!");
            throw new RuntimeException(e);
        }
    }
    
    public boolean atualizarPessoa(Pessoa pessoa) {

        boolean retorno = false;

        String atualizaPessoaSQL
                = "UPDATE pessoa SET "
                + "nome = ?,"
                + "data_nascimento = ?,"
                + "cpf = ? "
                + "WHERE id_pessoa = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(atualizaPessoaSQL);

            instrucao.setString(1, pessoa.getNome());
            instrucao.setDate(2, new java.sql.Date(pessoa.getDataNascimento().getTime()));
            instrucao.setString(3, pessoa.getCpf());

            int linhasAfetadasPessoa = instrucao.executeUpdate();
            instrucao.close();

            retorno = linhasAfetadasPessoa > 0;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Atualização de Pessoa!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }

    }
    
    public boolean atualizarFuncionario(Funcionario funcionario) {

        boolean retorno = false;
        
        if(!atualizarPessoa(funcionario)) return retorno;

        String atualizaFuncionarioSQL
                = "UPDATE funcionario SET "
                + "id_cargo = ?,"
                + "id_departamento = ?,"
                + "id_unidade_empresa = ? "
                + "WHERE id_funcionario = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(atualizaFuncionarioSQL);

            instrucao.setInt(1, funcionario.getCargo().getIdCargo());
            instrucao.setInt(2, funcionario.getDepartamento().getIdDepartamento());
            instrucao.setInt(3, funcionario.getUnidadeEmpresa().getIdUnidadeEmpresa());
            instrucao.setInt(4, funcionario.getIdFuncionario());

            int linhasAfetadasFuncionario = instrucao.executeUpdate();
            instrucao.close();

            retorno = linhasAfetadasFuncionario > 0;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Atualização de Funcionário!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }

    }
    
    /* Método ainda sem utilidade
    public boolean atualizarCliente(Cliente cliente) {

        boolean retorno = false;
        
        if(!atualizarPessoa(cliente)) return retorno;

        String atualizaClienteSQL
                = "UPDATE cliente SET "
                + "id_cargo = ?,"
                + "id_departamento = ?,"
                + "id_unidade_empresa = ? "
                + "WHERE id_funcionario = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(atualizaClienteSQL);

            instrucao.setInt(1, funcionario.getCargo().getIdCargo());
            instrucao.setInt(2, funcionario.getDepartamento().getIdDepartamento());
            instrucao.setInt(3, funcionario.getUnidadeEmpresa().getIdUnidadeEmpresa());
            instrucao.setInt(4, funcionario.getIdFuncionario());

            int linhasAfetadasFuncionario = instrucao.executeUpdate();
            instrucao.close();

            retorno = linhasAfetadasFuncionario > 0;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Atualização de Funcionário!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }

    }*/
    
    public boolean excluirPessoa(int id) {

        boolean retorno = false;

        String deletaPessoaSQL = "DELETE FROM pessoa WHERE id_pessoa = ?";
        String deletaFuncionarioSQL = "DELETE FROM funcionario WHERE id_pessoa = ?";
        String deletaClienteSQL = "DELETE FROM cliente WHERE id_pessoa = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(deletaClienteSQL);
            instrucao.setInt(1, id);
            int linhasAfetadasCliente = instrucao.executeUpdate();
            instrucao.close();

            instrucao = conexao.prepareStatement(deletaFuncionarioSQL);
            instrucao.setInt(1, id);
            int linhasAfetadasFuncionario = instrucao.executeUpdate();
            instrucao.close();
            
            instrucao = conexao.prepareStatement(deletaPessoaSQL);
            instrucao.setInt(1, id);
            int linhasAfetadasPessoa = instrucao.executeUpdate();
            instrucao.close();
            
            retorno = linhasAfetadasPessoa > 0;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Exclusão de Pessoa!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
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
        // setCargo e consulta
        // setDepartamento e consulta
        // setUnidadeEmpresa e consulta
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
                + "cliente.id_cliente,"
                + "pessoa.id_pessoa,"
                + "pessoa.nome,"
                + "pessoa.data_nascimento,"
                + "pessoa.cpf "
                + "FROM pessoa "
                + "JOIN cliente ON "
                + "cliente.id_pessoa = pessoa.id_pessoa "
                + "AND pessoa.cpf = ?";
        
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
            System.out.println("Erro na operação de Busca de Cliente!");
            throw new RuntimeException(e);
        }
    }
    
    public Funcionario buscarFuncionarioPorCPF(String cpf){
        String codigoSQL
                = "SELECT "
                + "funcionario.id_funcionario,"
                + "funcionario.id_cargo,"
                + "funcionario.id_departamento,"
                + "funcionario.id_unidade_empresa,"
                + "pessoa.id_pessoa,"
                + "pessoa.nome,"
                + "pessoa.data_nascimento,"
                + "pessoa.cpf "
                + "FROM pessoa "
                + "JOIN funcionario ON "
                + "funcionario.id_pessoa = pessoa.id_pessoa "
                + "AND pessoa.cpf = ?";
        
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
            System.out.println("Erro na operação de Busca de Funcionário!");
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
