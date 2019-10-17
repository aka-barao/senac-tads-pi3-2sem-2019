/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vinicius
 */
public class Produto {

    protected int id;
    protected Map<UnidadeEmpresa, Integer> quantidadeEstoque = new HashMap<UnidadeEmpresa, Integer>();
    protected double valor;
    protected String nome;
    protected String descricao;
    protected CategoriaProduto categoriaProduto;
    protected Date dataCadastro;

    // Construtor utilizado no fluxo de exclusão
    public Produto(int id) {
        this.id = id;
    }

    public Produto(int id, UnidadeEmpresa unidadeEmpresa, int quantidadeEstoque, double valor, String nome, String descricao) {
        this.id = id;
        this.quantidadeEstoque.put(unidadeEmpresa, quantidadeEstoque);
        this.valor = valor;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Produto(int id, double valor, String nome, String descricao, Date dataCadastro) {
        this.id = id;
        this.valor = valor;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
    }

    public Produto(double valor, String nome, String descricao, CategoriaProduto categoriaProduto) {
        this.valor = valor;
        this.nome = nome;
        this.descricao = descricao;
        this.categoriaProduto = categoriaProduto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Para implementar - Retornar estoque de determinada unidade
    public int getQuantidadeEstoque(UnidadeEmpresa unidadeEmpresa) {
        return quantidadeEstoque.get(this);
    }

    public void setQuantidadeEstoque(UnidadeEmpresa unidadeEmpresa, int quantidadeEstoque) {
        this.quantidadeEstoque.put(unidadeEmpresa, quantidadeEstoque);
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CategoriaProduto getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

}
