/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.models;

/**
 *
 * @author Marcos
 */
public class User {
    private String id;
    private String email;
    private String nome;
    private String empresa;
    
    public User(String _id, String _email, String _nome, String _empresa){
        this.id = _id;
        this.email = _email;
        this.nome = _nome;
        this.empresa = _empresa;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getEmpresa(){
        return this.empresa;
    }
}
