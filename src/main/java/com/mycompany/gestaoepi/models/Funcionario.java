/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.models;

/**
 *
 * @author Marcos
 */
public class Funcionario extends ModelBase{
    private String reg;
    private String email;
    private String setor;
    
    public Funcionario(String _id, String _reg, String _nome, String _email, String _setor){
        super(_id, _nome);
        this.reg = _reg;
        this.email = _email;
        this.setor = _setor;
    }
    
    public String getReg(){
        return this.reg;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getSetor(){
        return this.setor;
    }
    
    @Override
    public String toString(){
        return this.getNome();
    }
}
