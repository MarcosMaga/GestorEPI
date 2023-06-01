/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestaoepi.controllers;

import com.mycompany.gestaoepi.App;
import com.mycompany.gestaoepi.components.factories.ButtonFactory;
import com.mycompany.gestaoepi.dao.SetorDao;
import com.mycompany.gestaoepi.helpers.ScreenHelpers;
import com.mycompany.gestaoepi.models.Epi;
import com.mycompany.gestaoepi.models.Setor;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class SetorGestorController extends ScreenHelpers implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private List<Setor> setores;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private ComboBox<Setor> cb_setor;
    
    @FXML
    private TextField tf_search;
    
    @FXML
    private TextField tf_cod;
    
    @FXML
    private TextField tf_nome;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ButtonFactory factory = new ButtonFactory();
        rootPane.getChildren().add(factory.createButton("backbutton"));
        SetorDao dao = new SetorDao();
        setores = dao.SelectSetor(App.usuario.getId());
        ObservableList<Setor> obsSetor = FXCollections.observableArrayList(setores);
        cb_setor.setItems(obsSetor);
    }
    
    public void saveData(){
        if(tf_cod.getText().length() > 0 && tf_cod.getText().length() <= 25)
            if(tf_nome.getText().length() > 0 && tf_cod.getText().length() <= 100){
                SetorDao data = new SetorDao();
                data.updateSetor(App.usuario.getId(), cb_setor.getValue().getId(), tf_cod.getText(), tf_nome.getText());
                App.changeMainScreen("DashScreen");
            }
    }
    
    public void setData(){
        if(cb_setor.getValue() != null){
            tf_cod.setText(cb_setor.getValue().getCod());
            tf_nome.setText(cb_setor.getValue().getNome());
        }
    }
    
    public void delete(){
        Alert alert = new Alert(AlertType.CONFIRMATION, "Você realmente deseja deletar este setor?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        
        if(alert.getResult() == ButtonType.YES){
            SetorDao dao = new SetorDao();
            dao.deleteSetor(App.usuario.getId(), cb_setor.getValue().getId());
            App.changeMainScreen("DashScreen");
        }
    }
    
    public void searchData(){
        cb_setor.valueProperty().set(null);
        SetorDao dao = new SetorDao();
        setores = dao.SelectSetorByName(App.usuario.getId(), tf_search.getText().toUpperCase());
        ObservableList<Setor> obsSetor = FXCollections.observableArrayList(setores);
        cb_setor.setItems(obsSetor);
        cb_setor.setValue(null);
        cb_setor.setPromptText("Atualizado");
    }
}
