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
    private String cargo;
    private UnidadeEmpresa unidadeEmpresa;
    private int idFuncionario;
    
    public Funcionario(){
        
    }
            
    public Funcionario(int id, String nome, Date dataNascimento, String cpf, String Cargo){
        super(id, nome, dataNascimento, cpf);
        this.cargo = Cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String Cargo) {
        this.cargo = Cargo;
    }

    public UnidadeEmpresa getUnidadeEmpresa() {
        return unidadeEmpresa;
    }

    public void setUnidadeEmpresa(UnidadeEmpresa unidadeEmpresa) {
        this.unidadeEmpresa = unidadeEmpresa;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
    
    
}
