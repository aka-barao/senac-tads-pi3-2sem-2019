/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import model.Cargo;

/**
 *
 * @author samue
 */
public class Funcionario extends Pessoa{
    private Cargo cargo;
    private UnidadeEmpresa unidadeEmpresa;
    private int idFuncionario;
    private Departamento departamento;
    
    public Funcionario(){
    }
            
    public Funcionario(int idPessoa, String nome, Date dataNascimento, String cpf, Cargo Cargo){
        super(idPessoa, nome, dataNascimento, cpf);
        this.cargo = Cargo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.idFuncionario;
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
        final Funcionario other = (Funcionario) obj;
        if (this.idFuncionario != other.idFuncionario) {
            return false;
        }
        return true;
    }
    
    
}
