/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.models;

/**
 *
 * @author Marcos
 */
public class User extends ModelBase{
    private String email;
    private String empresa;
    
    public User(String _id, String _email, String _nome, String _empresa){
        super(_id,_nome);
        this.email = _email;
        this.empresa = _empresa;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getEmpresa(){
        return this.empresa;
    }
}
