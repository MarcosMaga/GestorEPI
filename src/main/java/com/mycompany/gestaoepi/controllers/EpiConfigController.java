/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestaoepi.controllers;

import com.mycompany.gestaoepi.App;
import com.mycompany.gestaoepi.components.factories.ButtonFactory;
import com.mycompany.gestaoepi.dao.EpiDao;
import com.mycompany.gestaoepi.helpers.ScreenHelpers;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class EpiConfigController extends ScreenHelpers implements Initializable {
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField tf_nome;
    
    @FXML
    private ComboBox<Integer> cb_quant;
    
    @FXML
    private TextField tf_ca;
    
    @FXML
    private TextField tf_marca;
    
    @FXML
    private TextField tf_vdUtil;
    
    @FXML
    private Label lb_status;
            
    @FXML
    private DatePicker dp_data;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ButtonFactory factory = new ButtonFactory();
        rootPane.getChildren().add(factory.createButton("backbutton"));
        List<Integer> values = setNumberValues(1, 2500);
        ObservableList<Integer> obsInt = FXCollections.observableArrayList(values);
        cb_quant.setItems(obsInt);
    }
    
    public void saveData(){
        lb_status.setVisible(true);
        if(verifyFields()){
            try{
                EpiDao dao = new EpiDao();
                LocalDate date = dp_data.getValue();
                dao.insertEpi(App.usuario.getId(), tf_nome.getText().toUpperCase(), tf_ca.getText().toUpperCase(), tf_marca.getText().toUpperCase(), date.toString(), tf_vdUtil.getText(), cb_quant.getValue().toString());
                lb_status.setStyle("-fx-background-color: #DFF0D8;");
                lb_status.setStyle("-fx-border-color: green;");
                lb_status.setTextFill(Color.GREEN);
                lb_status.setText("EPI " + tf_nome.getText() + " criado com sucesso");
                tf_nome.setText("");
                tf_ca.setText("");
                tf_marca.setText("");
                tf_vdUtil.setText("");
            }
            catch(Exception ex){
                lb_status.setStyle("-fx-background-color: ##F2DEDE;");
                lb_status.setStyle("-fx-border-color: red;");
                lb_status.setTextFill(Color.RED);
                lb_status.setText("Falha ao  criar EPI " + tf_nome.getText() + ". \nErro: " + ex.getMessage());
            }
        }
        else{
            lb_status.setStyle("-fx-background-color: ##F2DEDE;");
            lb_status.setStyle("-fx-border-color: red;");
            lb_status.setTextFill(Color.RED);
            lb_status.setText("Valores nulos ou incompletos ");
        }
    
    }
    
    private boolean verifyFields(){
        if(tf_nome.getText().isBlank() || tf_ca.getText().isBlank() || tf_marca.getText().isBlank() || tf_vdUtil.getText().isBlank() || dp_data.getValue() == null || cb_quant.getValue() == null)
            return false;
        
        try{
            int a = Integer.parseInt(tf_vdUtil.getText()); 
        }
        catch(Exception ex){
            return false;
        }
        
        if(tf_nome.getText().length() > 250 || tf_ca.getText().length() > 25 || tf_marca.getText().length() > 100)
            return false;
        
        return true;
    }
    
}
