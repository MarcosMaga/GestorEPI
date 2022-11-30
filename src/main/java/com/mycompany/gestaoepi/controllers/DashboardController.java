/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestaoepi.controllers;

import com.mycompany.gestaoepi.App;
import com.mycompany.gestaoepi.models.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class DashboardController implements Initializable {
   
    @FXML
    private Label tf_display;
    
    @FXML
    private ScrollPane sp_dash;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.setDashScreen(sp_dash);
        tf_display.setText(App.usuario.getEmpresa() + " | " + App.usuario.getNome());
        App.changeMainScreen("DashScreen");
    }

    public void exit(){
        App.changeScreen("Login");
    }
    
    public void navigateSetor(ActionEvent e){
        Button a = (Button)e.getSource();
        String value = a.getId();
        
        if(value.equals("novo_setor"))
            try{
                App.changeMainScreen("SetorConfig");
            }catch(Exception ex){
                System.out.println("Deu ruim");
            }
        else if(value.equals("gestor_setor"))
            try{
                App.changeMainScreen("SetorGestor");
            }catch(Exception ex){
                System.out.println("Deu ruim");
            }
        
        
    }
    
    public void navigateEntrega(ActionEvent e){
        Button a = (Button)e.getSource();
        String value = a.getId();
        
        if(value.equals("nova_setor"))
            try{
                App.changeMainScreen("EntregaSetorConfig");
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        else if(value.equals("nova_func"))
            try{
                App.changeMainScreen("EntregaFuncConfig");
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        else if(value.equals("gestor_entrega"))
            try{
                App.changeMainScreen("EntregaGestor");
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        else if(value.equals("arquivo_entrega"))
            try{
                App.changeMainScreen("ArquivoEntrega");
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
    }
    
    public void navigateFunc(ActionEvent e){
        Button a = (Button)e.getSource();
        String value = a.getId();
        
        if(value.equals("novo_func"))
            try{
                App.changeMainScreen("FuncConfig");
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        else if(value.equals("gestor_func"))
            try{
                App.changeMainScreen("FuncGestor");
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
    }
    
    public void navigateEpi(ActionEvent e){
        Button a = (Button)e.getSource();
        String value = a.getId();
        
        if(value.equals("novo_epi"))
            try{
                App.changeMainScreen("EpiConfig");
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        else if(value.equals("gestor_epi"))
            try{
                App.changeMainScreen("EpiGestor");
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
    }
}
