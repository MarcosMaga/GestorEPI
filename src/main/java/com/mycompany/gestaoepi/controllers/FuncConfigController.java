/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestaoepi.controllers;

import com.mycompany.gestaoepi.App;
import com.mycompany.gestaoepi.components.factories.ButtonFactory;
import com.mycompany.gestaoepi.dao.FuncDao;
import com.mycompany.gestaoepi.dao.SetorDao;
import com.mycompany.gestaoepi.helpers.ScreenHelpers;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class FuncConfigController extends ScreenHelpers implements Initializable {
    
    private List<Setor> setores;
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    private ComboBox<Setor> cb_setor;
    
    @FXML
    private TextField tf_reg;
    
    @FXML
    private TextField tf_nome;
    
    @FXML
    private TextField tf_email;
    
    @FXML
    private Label lb_status;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ButtonFactory factory = new ButtonFactory();
        rootPane.getChildren().add(factory.createButton("backbutton"));
        SetorDao setordao = new SetorDao();
        setores = setordao.SelectSetor(App.usuario.getId());
        ObservableList<Setor> obsSetor = FXCollections.observableArrayList(setores);
        cb_setor.setItems(obsSetor);
    }
    
    public void saveData(){
        lb_status.setVisible(true);
        if(verifyFields()){
            FuncDao dao = new FuncDao();
            Setor a = cb_setor.getValue();
            
            try{
                dao.insertFunc(App.usuario.getId(), tf_reg.getText(), tf_nome.getText().toUpperCase(), a.getId(), tf_email.getText());
                lb_status.setStyle("-fx-background-color: #DFF0D8;");
                lb_status.setStyle("-fx-border-color: green;");
                lb_status.setTextFill(Color.GREEN);
                lb_status.setText("Funcioario " + tf_nome.getText() + " criado com sucesso");
                tf_reg.setText("");
                tf_nome.setText("");
                tf_email.setText("");
            }
            catch(Exception ex){
                lb_status.setStyle("-fx-background-color: ##F2DEDE;");
                lb_status.setStyle("-fx-border-color: red;");
                lb_status.setTextFill(Color.RED);
                lb_status.setText("Falha ao  criar Funcioario " + tf_nome.getText() + ". \nErro: " + ex.getMessage());
            }
            
        }
        else
        {
            lb_status.setStyle("-fx-background-color: ##F2DEDE;");
            lb_status.setStyle("-fx-border-color: red;");
            lb_status.setTextFill(Color.RED);
            lb_status.setText("Valores nulos ou incompletos ");
        }
    }
    
    private boolean emailValidator(String val){
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(val);
        return matcher.matches();
    }
    
    private boolean verifyFields(){
        if(tf_reg.getText().isBlank() || tf_nome.getText().isBlank() || cb_setor.getValue() == null || emailValidator(tf_email.getStyle()))
            return false;
            
        if(tf_reg.getText().length() > 25 || tf_reg.getText().length() > 100)
            return false;
        
        return true;
    }
}
