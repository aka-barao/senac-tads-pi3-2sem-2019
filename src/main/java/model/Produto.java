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

    protected Integer id;
    protected Map<UnidadeEmpresa, Integer> quantidadeEstoque = new HashMap<>();
    protected double valor;
    protected String nome;
    protected String descricao;
    protected CategoriaProduto categoriaProduto;
    protected Date dataCadastro;
    
    public Produto(){
        
    }

    // Construtor utilizado no fluxo de exclusão
    public Produto(Integer id) {
        this.id = id;
    }

    public Produto(Integer id, UnidadeEmpresa unidadeEmpresa, Integer quantidadeEstoque, double valor, String nome, String descricao) {
        this.id = id;
        this.quantidadeEstoque.put(unidadeEmpresa, quantidadeEstoque);
        this.valor = valor;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Produto(Integer id, double valor, String nome, String descricao, Date dataCadastro) {
        this.id = id;
        this.valor = valor;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
    }
    
    public Produto(Integer id, double valor, String nome, String descricao, CategoriaProduto categoriaProduto) {
        this.id = id;
        this.valor = valor;
        this.nome = nome;
        this.descricao = descricao;
        this.categoriaProduto = categoriaProduto;
    }
    
    public Produto(double valor, String nome, String descricao, CategoriaProduto categoriaProduto) {
        this.valor = valor;
        this.nome = nome;
        this.descricao = descricao;
        this.categoriaProduto = categoriaProduto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantidadeEstoque(UnidadeEmpresa unidadeEmpresa) {
        return quantidadeEstoque.get(unidadeEmpresa);
    }

    public void setQuantidadeEstoque(UnidadeEmpresa unidadeEmpresa, Integer quantidadeEstoque) {
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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    

}
