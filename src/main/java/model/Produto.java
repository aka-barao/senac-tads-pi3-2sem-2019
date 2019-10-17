/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author vinicius
 */
public class Produto {

    private int id;
    private int quantidadeEstoque;
    private double valor;
    private String nome;
    private String descricao;
    private CategoriaProduto categoriaProduto;
    private Date dataCadastro;

    // Construtor utilizado no fluxo de exclusão
    public Produto(int id) {
        this.id = id;
    }

    public Produto(int id, int quantidadeEstoque, double valor, String nome, String descricao) {
        this.id = id;
        this.quantidadeEstoque = quantidadeEstoque;
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

    public Produto(double valor, String nome, String descricao) {
        this.valor = valor;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
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
