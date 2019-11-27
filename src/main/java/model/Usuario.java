/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author vinicius
 */
public class Usuario {
    
    private Integer id_usuario;
    private String nomeUsuario;
    private String senhaUsuario;
    private Integer id_funcionario;
    private Funcionario funcionario;

    public Usuario() {
    }

    public Usuario(Integer id_usuario, String nomeUsuario, String senhaUsuario, Integer id_funcionario) {
        this.id_usuario = id_usuario;
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.id_funcionario = id_funcionario;
    }
    
    public Usuario(String nomeUsuario, String senhaUsuario, Integer id_funcionario) {
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.id_funcionario = id_funcionario;
    }

    public Usuario(Integer id_usuario, String nomeUsuario, String senhaUsuario, Integer id_funcionario, Funcionario funcionario) {
        this.id_usuario = id_usuario;
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.id_funcionario = id_funcionario;
        this.funcionario = funcionario;
    }

    public Usuario(Integer id_usuario, String nomeUsuario, String senhaUsuario, Funcionario funcionario) {
        this.id_usuario = id_usuario;
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.funcionario = funcionario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }
    
    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Integer id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    
    
}
