/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author samue
 */
public class Venda {
    
    private int IdVenda;
    private Date DataVenda;
    private List<ItemVenda> itemVenda;
    private Cliente Cliente;
    
    public Venda(Date HoraVenda, Cliente Cliente) {
        this.DataVenda = HoraVenda;
        this.Cliente = Cliente;
    }
    
    public int getIdVenda() {
        return IdVenda;
    }

    public List<ItemVenda> getItens() {
        return itemVenda;
    }

    public void addItem(ItemVenda itemVenda) {
        this.itemVenda.add(itemVenda);
    }

    public void removeItem(ItemVenda itemVenda) {
        this.itemVenda.remove(itemVenda);
    }
    
    public Cliente getCliente() {
        return Cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.Cliente = Cliente;
    }
    
    public Date getDataVenda() {
        return DataVenda;
    }

    public void setDataVenda(Date DataVenda) {
        this.DataVenda = DataVenda;
    }
    
    
    
}
