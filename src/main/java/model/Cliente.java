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
    
    public Cliente(int id, String nome, Date dataNascimento, String cpf) {
        super(id, nome, dataNascimento, cpf);
    }
    
}
