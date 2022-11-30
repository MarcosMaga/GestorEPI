/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestaoepi.controllers;

import com.mycompany.gestaoepi.App;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class ArquivoEntregaController extends ScreenHelpers implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private List<Entrega> entregas;
    
    private int pages;
    private int actualPage=1;
    private int teste=0;
    
    @FXML
    private GridPane gp_dados;
    
    @FXML
    private Label lb_pag;
    
    @FXML
    private TextField tf_search;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchData("");
    }
    
    public void searchDataButton(){
        searchData(tf_search.getText());
    }
    
    public void searchData(String nome){
        if(entregas != null)
            entregas.clear();
        EntregaDao dao = new EntregaDao();
        entregas = dao.SelectEntregaArquivada(App.usuario.getId(), nome);
        pages = (int) Math.ceil(entregas.size() / 10.0);
        lb_pag.setText("Página: " + actualPage + " - " + pages);
        returnData();
    }
    
    public void deletarEntrega(ActionEvent e){
        Button a = (Button)e.getSource();
        String value = a.getId();
        
        System.out.println(value);
        value = value.replace("bt_excluir_", "");
        int index = Integer.parseInt(value);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você realmente deseja apagar uma entrega arquivada?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
                
        if(alert.getResult() == ButtonType.YES){
            EntregaDao dao = new EntregaDao();
            dao.deleteEntrega(App.usuario.getId(), entregas.get(index).getId());
            App.changeMainScreen("DashScreen");
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
    
    private void returnData(){
        clearGrid();
        int index = entregas.size() > 10 ? 10 : entregas.size();
        int j= actualPage * 10 - 10;
        
        if(j > entregas.size())
            j -= entregas.size();
        
       
        for(int i=0; i < index; i++, j++){
            Text value1 = new Text();
            value1.setText(formatData(entregas.get(j).getData_entrega()));
            value1.setWrappingWidth(84.13671875);
            value1.setTextAlignment(TextAlignment.CENTER);
            gp_dados.add(value1, 0, i+1);
            Text value2 = new Text();
            value2.setText(entregas.get(j).getEpi());
            value2.setWrappingWidth(132.13671875);
            value2.setTextAlignment(TextAlignment.CENTER);
            gp_dados.add(value2, 1, i+1);
            Text value3 = new Text(entregas.get(j).getFunc());
            value3.setWrappingWidth(128.13671875);
            value3.setTextAlignment(TextAlignment.CENTER);
            gp_dados.add(value3, 2, i+1);
            Text value5 = new Text(Integer.toString(entregas.get(j).getQuant()));
            value5.setWrappingWidth(42.13671875);
            value5.setTextAlignment(TextAlignment.CENTER);
            gp_dados.add(value5, 3, i+1);
            Label value6 = new Label(formatData(entregas.get(j).getData_devolucao()));
            value6.setPrefHeight(35);
            value6.setPrefWidth(79);
            value6.setAlignment(Pos.CENTER);
            gp_dados.add(value6, 4, i+1);
            
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
                
            
            gp_dados.add(value7, 5, i+1);
            
            Button bt = new Button("❌");
            bt.setPrefHeight(25);
            bt.setPrefWidth(65);
            bt.setStyle("-fx-text-fill: white; -fx-background-color: #D9534F;");
            bt.setId("bt_excluir_" + i);
            bt.setOnAction((event) -> {
                deletarEntrega(event);
            });
            gp_dados.add(bt, 6, i+1);
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
