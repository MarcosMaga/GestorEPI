/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.models;

/**
 *
 * @author Marcos
 */
public class Setor extends ModelBase {
    private String id;
    private String cod;
    private String nome;
    
    public Setor(String _id, String _cod, String _nome){
        super(_id, _nome);
        this.cod = _cod;
    }
    
    public String getCod(){
        return this.cod;
    }
     
    @Override
    public String toString(){
        return this.nome;
    }
}
