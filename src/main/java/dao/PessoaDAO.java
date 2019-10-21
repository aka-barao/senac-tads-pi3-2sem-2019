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
import model.CategoriaProduto;
import model.Cliente;
import model.Funcionario;
import model.Pessoa;
import model.Produto;
import model.UnidadeEmpresa;

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
            System.out.println("Erro na operação de Cadastro de Produto!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }
    }

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
            System.out.println("Erro na operação de Consulta!");
            throw new RuntimeException(e);
        }
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
}
