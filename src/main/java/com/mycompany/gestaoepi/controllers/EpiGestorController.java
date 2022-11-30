/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestaoepi.controllers;

import com.mycompany.gestaoepi.App;
import com.mycompany.gestaoepi.dao.EpiDao;
import com.mycompany.gestaoepi.helpers.ScreenHelpers;
import com.mycompany.gestaoepi.models.Epi;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class EpiGestorController extends ScreenHelpers implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private List<Epi> epis;
    
    @FXML
    private ComboBox<Epi> cb_epi;
    
    @FXML
    private ComboBox<Integer> cb_quant;
    
    @FXML
    private TextField tf_search;
    
    @FXML
    private TextField tf_nome;
    
    @FXML
    private TextField tf_ca;
    
    @FXML
    private TextField tf_marca;
    
    @FXML
    private TextField tf_vida_util;
    
    @FXML
    private DatePicker dp_data;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EpiDao dao = new EpiDao();
        epis = dao.SelectEpi(App.usuario.getId());
        List<Integer> values = setNumberValues(1, 2500);
        ObservableList<Integer> obsInt = FXCollections.observableArrayList(values);
        ObservableList<Epi> obsSetor = FXCollections.observableArrayList(epis);
        cb_epi.setItems(obsSetor);
        cb_quant.setItems(obsInt);
    }
    
    public void setData(){
        if(cb_epi.getValue() != null){
            tf_nome.setText(cb_epi.getValue().getNome());
            tf_ca.setText(cb_epi.getValue().getCa());
            tf_marca.setText(cb_epi.getValue().getMarca());
            tf_vida_util.setText(Integer.toString(cb_epi.getValue().getVidaUtil()));
            Date a = cb_epi.getValue().getDataValidade();
            System.out.println(a.toString());
            LocalDate date = LocalDate.parse(a.toString());
            dp_data.setValue(date);
            cb_quant.setValue(cb_epi.getValue().getQuant());
        }
    }
    
    public void searchData(){
        cb_epi.valueProperty().set(null);
        EpiDao dao = new EpiDao();
        epis = dao.SelectEpiByName(App.usuario.getId(), tf_search.getText().toUpperCase());
        ObservableList<Epi> obsSetor = FXCollections.observableArrayList(epis);
        cb_epi.setItems(obsSetor);
        cb_epi.setValue(null);
        cb_epi.setPromptText("Atualizado");
    }
    
    public void setEpi(Epi epiReceive){
        cb_epi.setValue(epiReceive);
        tf_nome.setText(cb_epi.getValue().getNome());
        tf_ca.setText(cb_epi.getValue().getCa());
        tf_marca.setText(cb_epi.getValue().getMarca());
        tf_vida_util.setText(Integer.toString(cb_epi.getValue().getVidaUtil()));
        Date a = cb_epi.getValue().getDataValidade();
        System.out.println(a.toString());
        LocalDate date = LocalDate.parse(a.toString());
        dp_data.setValue(date);
        cb_quant.setValue(cb_epi.getValue().getQuant());
    }
    
    public void delete(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você realmente deseja deletar este EPI?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        
        if(alert.getResult() == ButtonType.YES){
            EpiDao dao = new EpiDao();
            dao.deleteEpi(App.usuario.getId(), cb_epi.getValue().getId());
            App.changeMainScreen("DashScreen");
        }
    }
    
    public void saveData(){
        Alert alert = new Alert(AlertType.ERROR, "Erro ao atualizar EPI");
        if(verifyFields()){
            EpiDao data = new EpiDao();
            data.updateEpi(App.usuario.getId(), cb_epi.getValue().getId(), tf_nome.getText().toUpperCase(), tf_ca.getText().toUpperCase(), tf_marca.getText().toUpperCase(), tf_vida_util.getText(), dp_data.getValue().toString(), cb_quant.getValue().toString());
            App.changeMainScreen("DashScreen");
        } else
            alert.showAndWait();
    }
    
    private boolean verifyFields(){
        if(tf_nome.getText().isBlank() || tf_ca.getText().isBlank() || tf_marca.getText().isBlank() || tf_vida_util.getText().isBlank() || dp_data.getValue() == null || cb_quant.getValue() == null)
            return false;
        
        try{
            int a = Integer.parseInt(tf_vida_util.getText()); 
        }
        catch(Exception ex){
            return false;
        }
        
        if(tf_nome.getText().length() > 250 || tf_ca.getText().length() > 25 || tf_marca.getText().length() > 100)
            return false;
        
        return true;
    }
   
}
