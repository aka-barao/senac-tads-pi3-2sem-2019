/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author samue
 */
public class ItemVenda {
    
    private int IdItemVenda;
    private Venda venda;
    private Produto produto;
    private int quantidade;
    private Double ValorUnitario;

    public ItemVenda(Venda venda, Produto produto, int quantidade, Double ValorUnitario) {
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.ValorUnitario = ValorUnitario;
    }
    
    public ItemVenda(int IdItemVenda, Venda venda, Produto produto, int quantidade, Double ValorUnitario) {
        this.IdItemVenda = IdItemVenda;
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.ValorUnitario = ValorUnitario;
    }
    
    public int getIdItemVenda() {
        return IdItemVenda;
    }

    public void setIdItemVenda(int IdItemVenda) {
        this.IdItemVenda = IdItemVenda;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return ValorUnitario;
    }

    public void setValorUnitario(Double ValorUnitario) {
        this.ValorUnitario = ValorUnitario;
    }
    
    
    
    
    
}
