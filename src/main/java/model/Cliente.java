/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author vinicius
 */
public class Cliente extends Pessoa{
    
    private int idCliente;

    public Cliente(){
    }

    public Cliente(int idCliente, int idPessoa, String nome, Date dataNascimento, String cpf) {
        super(idPessoa, nome, dataNascimento, cpf);
        this.idCliente = idCliente;
    }
    
    public Cliente( String nome, Date dataNascimento, String cpf) {
        super(nome, dataNascimento, cpf);
    }
    
    public Cliente(int id, String nome, Date dataNascimento, String cpf) {
        super(id, nome, dataNascimento, cpf);
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.idCliente;
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
        final Cliente other = (Cliente) obj;
        if (this.idCliente != other.idCliente) {
            return false;
        }
        return true;
    }

    
    
    
}
