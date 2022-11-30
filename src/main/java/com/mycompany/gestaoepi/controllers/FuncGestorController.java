/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestaoepi.controllers;

import com.mycompany.gestaoepi.App;
import com.mycompany.gestaoepi.dao.EpiDao;
import com.mycompany.gestaoepi.dao.FuncDao;
import com.mycompany.gestaoepi.dao.SetorDao;
import com.mycompany.gestaoepi.helpers.ScreenHelpers;
import com.mycompany.gestaoepi.models.Epi;
import com.mycompany.gestaoepi.models.Funcionario;
import com.mycompany.gestaoepi.models.Setor;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class FuncGestorController extends ScreenHelpers implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private List<Funcionario> funcionarios;
    private List<Setor> setores;
    
    @FXML
    private ComboBox<Funcionario> cb_func;
    
    @FXML
    private TextField tf_search;
    
    @FXML
    private TextField tf_reg;
    
    @FXML
    private TextField tf_nome;
    
    @FXML
    private ComboBox<Setor> cb_setor;
    
    @FXML
    private TextField tf_email;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FuncDao dao = new FuncDao();
        funcionarios = dao.SelectFunc(App.usuario.getId());
        ObservableList<Funcionario> obsFunc = FXCollections.observableArrayList(funcionarios);
        cb_func.setItems(obsFunc);
        SetorDao dao2 = new SetorDao();
        setores = dao2.SelectSetor(App.usuario.getId());
        ObservableList<Setor> obsSetor = FXCollections.observableArrayList(setores);
        cb_setor.setItems(obsSetor);
    }    
    
    public void setData(){
        if(cb_func.getValue() != null){
            tf_reg.setText(cb_func.getValue().getReg());
            tf_nome.setText(cb_func.getValue().getNome());
            tf_email.setText(cb_func.getValue().getEmail());
            cb_setor.setValue(null);
        
            for(Setor a: setores){
                if(a.getId().equals(cb_func.getValue().getSetor().trim())){
                    cb_setor.setValue(a);
                    break;
                }
            }
        }
    }
    
    public void delete(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você realmente deseja deletar este Funcionário?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        
        if(alert.getResult() == ButtonType.YES){
            FuncDao dao = new FuncDao();
            dao.deleteFunc(App.usuario.getId(), cb_func.getValue().getId());
            App.changeMainScreen("DashScreen");
        }
    }
    
    public void searchData(){
        cb_func.valueProperty().set(null);
        FuncDao dao = new FuncDao();
        funcionarios = dao.SelectFuncByName(App.usuario.getId(), tf_search.getText().toUpperCase());
        ObservableList<Funcionario> obsSetor = FXCollections.observableArrayList(funcionarios);
        cb_func.setItems(obsSetor);
        cb_func.setValue(null);
        cb_func.setPromptText("Atualizado");
    }
    
    public void saveData(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao atualizar EPI");
        if(verifyFields()){
            FuncDao dao = new FuncDao();
            dao.updateFunc(App.usuario.getId(), cb_func.getValue().getId(), tf_nome.getText().toUpperCase(), tf_reg.getText().toUpperCase(), cb_setor.getValue().getId(), tf_email.getText().toLowerCase());
            App.changeMainScreen("DashScreen");
        } else
            alert.showAndWait();
    }
    
    private boolean verifyFields(){
        if(tf_reg.getText().isBlank() || tf_nome.getText().isBlank() || cb_setor.getValue() == null || tf_email.getText().isBlank() || emailValidator(tf_email.getStyle()))
            return false;
            
        if(tf_reg.getText().length() > 25 || tf_reg.getText().length() > 100)
            return false;
        
        return true;
    }
    
    private boolean emailValidator(String val){
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(val);
        return matcher.matches();
    }
    
}
