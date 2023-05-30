/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.models;

/**
 *
 * @author Marcos
 */
public class Funcionario {
    private String id;
    private String reg;
    private String nome;
    private String email;
    private String setor;
    
    public Funcionario(String _id, String _reg, String _nome, String _email, String _setor){
        this.id = _id;
        this.reg = _reg;
        this.nome = _nome;
        this.email = _email;
        this.setor = _setor;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getReg(){
        return this.reg;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getSetor(){
        return this.setor;
    }
    
    @Override
    public String toString(){
        return this.nome;
    }
}
