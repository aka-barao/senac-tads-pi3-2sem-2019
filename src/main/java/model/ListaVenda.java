/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author samue
 */
public class ListaVenda {
    private ArrayList<Produto> ListaItemVenda;
    
    public void adicionarProduto(Produto Produto){
        ListaItemVenda.add(Produto);
    }
    
    public void removerProduto(Produto Produto){
        ListaItemVenda.remove(Produto);
    }

    public ArrayList<Produto> getListaItemVenda() {
        return ListaItemVenda;
    }

    public void setListaItemVenda(ArrayList<Produto> ListaItemVenda) {
        this.ListaItemVenda = ListaItemVenda;
    }
    
    
}
