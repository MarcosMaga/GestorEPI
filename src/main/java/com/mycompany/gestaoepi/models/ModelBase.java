/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.models;

/**
 *
 * @author Marcos
 */
public class ModelBase {
    private String id;
    private String name;
    
    public ModelBase(String _id, String _name){
        this.id = _id;
        this.name = _name;
    }
    
    public String getNome(){
        return this.name;
    }
    
    public String getId(){
        return this.id;
    }
}
