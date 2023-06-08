/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.components;

import com.mycompany.gestaoepi.App;
import com.mycompany.gestaoepi.controllers.DevolverGestorController;
import com.mycompany.gestaoepi.dao.EntregaDao;
import com.mycompany.gestaoepi.models.Entrega;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 *
 * @author Marcos
 */
public class DevolverButton extends ButtonConcret{
    public DevolverButton(String id){
        super("Devolver");
        this.setStyle("-fx-text-fill: white; -fx-background-color: #337AB7;");
        this.setPrefHeight(25);
        this.setPrefWidth(65);
        this.setId(id);
    }
    
    @Override
    public void action(ActionEvent e) {
        EntregaDao dao = new EntregaDao();
        Button botao = (Button)e.getSource();
        String value = botao.getId();
        value = value.replace("bt_devolver_", "");
        System.out.println(value);
        Entrega entrega = dao.SelectEntregaById(App.usuario.getId(), value);
        System.out.println(entrega.toString());
        DevolverGestorController controller = (DevolverGestorController) App.changeMainScreenData("DevolverGestor");
        controller.setData(entrega);
    }
    
}
