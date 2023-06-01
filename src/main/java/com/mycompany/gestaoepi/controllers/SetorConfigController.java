/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestaoepi.controllers;

import com.mycompany.gestaoepi.App;
import com.mycompany.gestaoepi.components.factories.ButtonFactory;
import com.mycompany.gestaoepi.dao.SetorDao;
import com.mycompany.gestaoepi.helpers.ScreenHelpers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Marcos
 */

public class SetorConfigController extends ScreenHelpers implements Initializable {
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField tf_cod;
    
    @FXML
    private TextField tf_nome;
    
    @FXML
    private Label lb_status;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ButtonFactory factory = new ButtonFactory();
        rootPane.getChildren().add(factory.createButton("backbutton"));
        // TODO
    }
    
    public void saveData(){
        if(tf_cod.getText().length() > 0 && tf_cod.getText().length() <= 25)
            if(tf_nome.getText().length() > 0 && tf_cod.getText().length() <= 100){
                SetorDao data = new SetorDao();
                
                try{
                    data.insertSetor(App.usuario.getId(), tf_cod.getText().toUpperCase(), tf_nome.getText().toUpperCase());
                    lb_status.setTextFill(Color.GREEN);
                    lb_status.setText("Sucesso ao cadastrar setor: " + tf_cod.getText().toUpperCase() + ".");
                    tf_nome.setStyle("-fx-border-color: none");
                    tf_cod.setStyle("-fx-border-color: none");
                } catch(Exception ex){
                    lb_status.setTextFill(Color.RED);
                    lb_status.setText("Falha ao cadastrar setor.");
                    tf_nome.setStyle("-fx-border-color: red");
                    tf_cod.setStyle("-fx-border-color: red");
                }
            }else{
                lb_status.setTextFill(Color.RED);
                lb_status.setText("Nome do setor invalido");
                tf_nome.setStyle("-fx-border-color: red");
            }
        else{
            lb_status.setTextFill(Color.RED);
            lb_status.setText("Código do setor invalido");
            tf_cod.setStyle("-fx-border-color: red");
        }
                
    }
}
