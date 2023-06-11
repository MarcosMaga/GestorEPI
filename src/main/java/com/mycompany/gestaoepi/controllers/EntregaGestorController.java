/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestaoepi.controllers;

import com.mycompany.gestaoepi.App;
import com.mycompany.gestaoepi.components.ConfirmLabel;
import com.mycompany.gestaoepi.components.DevolverButton;
import com.mycompany.gestaoepi.components.TextData;
import com.mycompany.gestaoepi.components.VencimentoLabel;
import com.mycompany.gestaoepi.components.factories.ButtonFactory;
import com.mycompany.gestaoepi.dao.EntregaDao;
import com.mycompany.gestaoepi.helpers.ScreenHelpers;
import com.mycompany.gestaoepi.models.Entrega;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class EntregaGestorController extends ScreenHelpers implements Initializable {
    
    private List<Entrega> entregas;
    
    private int pages;
    private int actualPage=1;
    private int teste=0;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private GridPane gp_dados;
    
    @FXML
    private Label lb_pag;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ButtonFactory factory = new ButtonFactory();
        rootPane.getChildren().add(factory.createButton("backbutton"));
        EntregaDao dao = new EntregaDao();
        entregas = dao.SelectEntrega(App.usuario.getId());
        
        if(entregas != null){
            pages = (int) Math.ceil(entregas.size() / 10.0);
            lb_pag.setText("Página: " + actualPage + " - " + pages);
            returnData();
        } else {
            pages = 1;
            lb_pag.setText("Página: " + actualPage + " - " + pages);
        }
    }
    
    private void clearGrid(){
        List<Node> lista = gp_dados.getChildren();
        System.out.println(lista);
        
        for(int i=lista.size()-1; i > 7; i--){
            if(!lista.get(i).toString().contains("Group"))
                gp_dados.getChildren().remove(lista.get(i));
        }
       
    }

    public void returnData(){
        clearGrid();
        int index = entregas.size() > 10 ? 10 : entregas.size();
        int j= actualPage * 10 - 10;
        
        if(j > entregas.size())
            j -= entregas.size();
        
        for(int i=0; i < index; i++, j++){    
            gp_dados.add(new TextData(formatData(entregas.get(j).getData_entrega()),81.13671875), 0, i+1);
            gp_dados.add(new TextData(entregas.get(j).getEpi(), 109.13671875), 1, i+1);
            gp_dados.add(new TextData(entregas.get(j).getFunc(), 98.13671875), 2, i+1);
            gp_dados.add(new TextData(Integer.toString(entregas.get(j).getVida_util()), 42.13671875), 3, i+1);
            gp_dados.add(new TextData(Integer.toString(entregas.get(j).getQuant()), 37.13671875), 4, i+1);
            gp_dados.add(new VencimentoLabel(entregas.get(j).dataFaltante()), 5, i+1);
            gp_dados.add(new ConfirmLabel(entregas.get(j).isConfirmado()), 6, i+1);
            gp_dados.add(new DevolverButton("bt_devolver_" + entregas.get(i).getId()), 7, i+1);
        }
    }
    
    public void nextPage(){
        if(actualPage < pages){
            actualPage++;
            lb_pag.setText("Página: " + actualPage + " - " + pages);
            returnData();
        }
    }
    
    public void backPage(){
         if(actualPage > 1){
            actualPage--;
            lb_pag.setText("Página: " + actualPage + " - " + pages);
            returnData();
        }
    }
    
}
