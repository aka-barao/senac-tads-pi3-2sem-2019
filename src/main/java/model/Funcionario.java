/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author samue
 */
public class Funcionario extends Pessoa{
    private String Cargo;
            
    public Funcionario(int id, String nome, Date dataNascimento, String cpf, String Cargo){
        super(id, nome, dataNascimento, cpf);
        this.Cargo = Cargo;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }
    
    
}
