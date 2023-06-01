/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestaoepi.controllers;

import com.mycompany.gestaoepi.App;
import com.mycompany.gestaoepi.components.factories.ButtonFactory;
import com.mycompany.gestaoepi.dao.EntregaDao;
import com.mycompany.gestaoepi.helpers.ScreenHelpers;
import com.mycompany.gestaoepi.models.Entrega;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class DevolverGestorController extends ScreenHelpers implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private Label lb_data_entrega;
    
    @FXML
    private Label lb_epi;
    
    @FXML
    private Label lb_func;
    
    @FXML
    private Label lb_quant;
    
    @FXML
    private Label lb_data_previsao;
    
    @FXML
    private DatePicker dp_data_devolucao;
    
    private Entrega entrega;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ButtonFactory factory = new ButtonFactory();
        rootPane.getChildren().add(factory.createButton("backbutton"));
        // TODO
    }
    
    public void setData(Entrega a){
        lb_data_entrega.setText(formatData(a.getData_entrega()));
        lb_epi.setText(a.getEpi());
        lb_func.setText(a.getFunc());
        lb_quant.setText(Integer.toString(a.getQuant()));
        lb_data_previsao.setText(formatData(a.dataCompleta()));    
        entrega = a;
    }
    
    public void saveData(){
        if(dp_data_devolucao.getValue() != null){
            if(!entrega.isConfirmado()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você realmente deseja devolver uma entrega não confirmada?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                
                if(alert.getResult() == ButtonType.YES){
                    EntregaDao dao = new EntregaDao();
                    dao.updateEntregaDataDevolucao(App.usuario.getId(), entrega.getId(), dp_data_devolucao.getValue().toString());
                    App.changeMainScreen("EntregaGestor");
                }
                else
                    App.changeMainScreen("EntregaGestor");
            } else {
                EntregaDao dao = new EntregaDao();
                dao.updateEntregaDataDevolucao(App.usuario.getId(), entrega.getId(), dp_data_devolucao.getValue().toString());
                App.changeMainScreen("EntregaGestor");
            }
        }
    }
    
}
