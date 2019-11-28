/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author samue
 */
public class ClienteDAO {
    
    private PreparedStatement instrucao;
    
    
    public ClienteDAO() { // Avisa no console caso o programa consiga se conectar sem problemas ao BD;
        try {
            Connection testaConexao = new ConnectionFactory().getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            testaConexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados!");
        }

    }
    
    public boolean inserirNovoCliente(Cliente cliente){
        boolean retorno = false;

        String cadastraClienteSQL = "INSERT INTO cliente(nome, datanasc, cpf)"
                + " VALUES (?,?,?)";

        // try-with-resources || Conexão será aberta novamente dentro do "try" e fechada automaticamente ao final dele.
        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(cadastraClienteSQL);

            instrucao.setString(1, cliente.getNome());
            instrucao.setDate(2, (Date) cliente.getDataNascimento());
            instrucao.setString(3, cliente.getCpf());
            
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
    
    private Cliente instanciarCliente(ResultSet resultadoConsulta) throws SQLException {
        Cliente cliente = new Cliente(
                resultadoConsulta.getInt("cliente.id_cliente"),
                resultadoConsulta.getString("cliente.nome"),
                new java.util.Date(resultadoConsulta.getTimestamp("cliente.data_nascimento").getTime()),
                resultadoConsulta.getString("cliente.cpf")
        );

        return cliente;
    }
    
    public ArrayList<Cliente> listarClientes() {
        String codigoSQL
                = "SELECT "
                + "cliente.id_cliente,"
                + "cliente.nome,"
                + "cliente.data_nascimento,"
                + "cliente.cpf"
                + "FROM cliente";

        ArrayList<Cliente> listaDeClientes = new ArrayList<>();

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            ResultSet resultado = instrucao.executeQuery();
            
            while (resultado.next()) {
                if (resultado.getInt("cliente.id_cliente") != 0) {
                    Cliente cliente = instanciarCliente(resultado);
                    listaDeClientes.add(cliente);
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
    
    public void excluir(Integer codCliente) throws SQLException,ClassNotFoundException {
                String deletaProdutoSQL = "DELETE FROM produto WHERE id_produto = ?";
		Connection conexao = new ConnectionFactory().getConnection();
		instrucao = conexao.prepareStatement(deletaProdutoSQL);
		instrucao.setInt(1,codCliente);
		instrucao.execute();
    }
    
    public boolean atualizarCliente(Cliente cliente) {

        boolean retorno = false;

        String atualizaProdutoSQL
                = "UPDATE produto SET "
                + "nome = ?, "
                + "data_nascimento = ?, "
                + "cpf = ?, "
                + "WHERE id = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(atualizaProdutoSQL);

            instrucao.setString(1, cliente.getNome());
            instrucao.setDate(2, (Date) cliente.getDataNascimento());
            instrucao.setString(3, cliente.getCpf());
            instrucao.setInt(4, cliente.getIdCliente());

            int linhasAfetadasProduto = instrucao.executeUpdate();
            instrucao.close();

            retorno = linhasAfetadasProduto > 0;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Atualização de Produto!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }

    }
    
    
}
