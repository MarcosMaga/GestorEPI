/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestaoepi.controllers;

import com.mycompany.gestaoepi.App;
import com.mycompany.gestaoepi.dao.EntregaDao;
import com.mycompany.gestaoepi.dao.UsersDao;
import com.mycompany.gestaoepi.models.Entrega;
import com.mycompany.gestaoepi.models.User;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Marcos
 */

public class LoginController implements Initializable {

    @FXML
    private TextField tf_email;
    
    @FXML
    private PasswordField tf_pass;
    
    @FXML
    private Label lb_status;
    
    @FXML
    private TextField tf_code;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private boolean emailValidator(String val){
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(val);
        return matcher.matches();
    }
    
    public void tryLogin() throws NoSuchAlgorithmException{
        if(emailValidator(tf_email.getText()))
            if(tf_pass.getText().length() > 0){
                UsersDao usersDao = new UsersDao();
                User user = usersDao.getLoginUser(tf_email.getText(), App.HashPassword(tf_pass.getText()));
                
                if(user != null){
                    System.out.println("Logado");
                    App.usuario = user;
                    App.changeScreen("Dashboard");
                }
                else{
                    lb_status.setText("Senha ou Email inválidos");
                    tf_pass.setStyle("-fx-border-color: red");
                    tf_email.setStyle("-fx-border-color: red");
                }

            }
            else{
                lb_status.setText("Insira uma senha");
                tf_pass.setStyle("-fx-border-color: red");
            }
        else{
            lb_status.setText("Email invalido");
            tf_email.setStyle("-fx-border-color: red");
        }
    }
    
    public void tryAuthenticate(){
        EntregaDao dao = new EntregaDao();
        Entrega a = dao.ConfirmEntrega(tf_code.getText());
        
        if(a == null){
            tf_code.setStyle("-fx-border-color: red;");
        } else {
            tf_code.setStyle("-fx-border-color: none;");
            Alert alert = new Alert(AlertType.INFORMATION, "ENTREGA\nFuncionário: " + a.getFunc() + "\nEPI: " + a.getEpi());
            alert.showAndWait();
            tf_code.setText("");
        }
    }
    
}
