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
    
    public Cliente(int id, String nome, Date dataNascimento, String cpf) {
        super(id, nome, dataNascimento, cpf);
    }
    
}
