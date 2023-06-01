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
            Text value1 = new Text();
            value1.setText(formatData(entregas.get(j).getData_entrega()));
            value1.setWrappingWidth(81.13671875);
            value1.setTextAlignment(TextAlignment.CENTER);
            gp_dados.add(value1, 0, i+1);
            Text value2 = new Text();
            value2.setText(entregas.get(j).getEpi());
            value2.setWrappingWidth(109.13671875);
            value2.setTextAlignment(TextAlignment.CENTER);
            gp_dados.add(value2, 1, i+1);
            Text value3 = new Text(entregas.get(j).getFunc());
            value3.setWrappingWidth(98.13671875);
            value3.setTextAlignment(TextAlignment.CENTER);
            gp_dados.add(value3, 2, i+1);
            Text value4 = new Text(Integer.toString(entregas.get(j).getVida_util()));
            value4.setWrappingWidth(42.13671875);
            value4.setTextAlignment(TextAlignment.CENTER);
            gp_dados.add(value4, 3, i+1);
            Text value5 = new Text(Integer.toString(entregas.get(j).getQuant()));
            value5.setWrappingWidth(37.13671875);
            value5.setTextAlignment(TextAlignment.CENTER);
            gp_dados.add(value5, 4, i+1);
            Label value6 = new Label(Integer.toString(entregas.get(j).dataFaltante()));
            value6.setPrefHeight(36);
            value6.setPrefWidth(70);
            value6.setAlignment(Pos.CENTER);
            value6.setStyle("-fx-border-color: black");
            
            if(entregas.get(j).dataFaltante() <= 10)
                value6.setStyle("-fx-background-color: #FF6347");
            else if(entregas.get(j).dataFaltante() > 10 && entregas.get(j).dataFaltante() <= 30)
                value6.setStyle("-fx-background-color: #FFFF00");
            else
                value6.setStyle("-fx-background-color: #98FB98");
            
            gp_dados.add(value6, 5, i+1);
            
            Label value7 = new Label();
            value7.setPrefHeight(34);
            value7.setPrefWidth(38);
            value7.setAlignment(Pos.CENTER);
            
            if(entregas.get(j).isConfirmado()){
                value7.setStyle("-fx-text-fill: #98FB98");
                value7.setText("✔");
            }  
            else{
                value7.setStyle("-fx-text-fill: #FF6347");
                value7.setText("❌");
            }
                
            
            gp_dados.add(value7, 6, i+1);
            
            Button bt = new Button("Devolver");
            bt.setPrefHeight(25);
            bt.setPrefWidth(65);
            bt.setStyle("-fx-text-fill: white; -fx-background-color: #337AB7;");
            bt.setId("bt_devolver_" + i);
            bt.setOnAction((event) -> {
                devolverEntrega(event);
            });
            gp_dados.add(bt, 7, i+1);
        }
    }
    
    public void devolverEntrega(ActionEvent e){
        Button a = (Button)e.getSource();
        String value = a.getId();
        value = value.replace("bt_devolver_", "");
        int index = Integer.parseInt(value);
        DevolverGestorController controller = (DevolverGestorController) App.changeMainScreenData("DevolverGestor");
        controller.setData(entregas.get(index));
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
