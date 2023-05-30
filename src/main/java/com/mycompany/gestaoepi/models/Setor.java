/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.models;

/**
 *
 * @author Marcos
 */
public class Setor {
    private String id;
    private String cod;
    private String nome;
    
    public Setor(String _id, String _cod, String _nome){
        this.id = _id;
        this.cod = _cod;
        this.nome = _nome;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getCod(){
        return this.cod;
    }
    
    public String getNome(){
        return this.nome;
    }
        
    @Override
    public String toString(){
        return this.nome;
    }
}
