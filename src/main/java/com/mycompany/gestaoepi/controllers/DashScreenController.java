/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestaoepi.controllers;

import com.mycompany.gestaoepi.App;
import com.mycompany.gestaoepi.dao.EntregaDao;
import com.mycompany.gestaoepi.dao.EpiDao;
import com.mycompany.gestaoepi.helpers.ScreenHelpers;
import com.mycompany.gestaoepi.models.Entrega;
import com.mycompany.gestaoepi.models.Epi;
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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class DashScreenController extends ScreenHelpers implements Initializable {

    private List<Entrega> entregas;
    private List<Epi> epis;

    private int pages;
    private int actualPage = 1;

    private int pages2;
    private int actualPage2 = 1;

    @FXML
    private GridPane gp_dados;

    @FXML
    private GridPane gp_dados2;

    @FXML
    private Label lb_pag;

    @FXML
    private Label lb_pag2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EntregaDao dao = new EntregaDao();
        entregas = dao.SelectEntregaWeek(App.usuario.getId());
        EpiDao daoEpi = new EpiDao();
        epis = daoEpi.SelectEpiToVenc(App.usuario.getId());

        try {
            if (epis != null) {
                pages2 = (int) Math.ceil(epis.size() / 10.0);
                lb_pag2.setText("Página: " + actualPage2 + " - " + pages2);
                returnData2();
            } else {
                pages2 = 1;
                lb_pag2.setText("Página: " + actualPage + " - " + pages);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        try {
            if (entregas != null) {
                pages = (int) Math.ceil(entregas.size() / 10.0);
                lb_pag.setText("Página: " + actualPage + " - " + pages);
                returnData();
            } else {
                pages = 1;
                lb_pag.setText("Página: " + actualPage + " - " + pages);
            }
        } catch (Exception ex) {
            System.out.println("ruim aqui");
        }

    }

    private void clearGrid() {
        List<Node> lista = gp_dados.getChildren();
        System.out.println(lista);

        for (int i = lista.size() - 1; i > 7; i--) {
            if (!lista.get(i).toString().contains("Group")) {
                gp_dados.getChildren().remove(lista.get(i));
            }
        }
    }

    private void clearGrid2() {
        List<Node> lista = gp_dados.getChildren();
        System.out.println(lista);

        for (int i = lista.size() - 1; i > 5; i--) {
            if (!lista.get(i).toString().contains("Group")) {
                gp_dados.getChildren().remove(lista.get(i));
            }
        }
    }

    private void returnData2() {
        clearGrid2();
        int index = epis.size() > 10 ? 10 : epis.size();
        int j = actualPage2 * 10 - 10;

        if (j > epis.size()) {
            j -= entregas.size();
        }

        for (int i = 0; i < index; i++, j++) {
            Text value1 = new Text(epis.get(j).getCa());
            value1.setWrappingWidth(85.13671875);
            value1.setTextAlignment(TextAlignment.CENTER);
            gp_dados2.add(value1, 0, i + 1);
            Text value2 = new Text(epis.get(j).getNome());
            value2.setWrappingWidth(131.13671875);
            value2.setTextAlignment(TextAlignment.CENTER);
            gp_dados2.add(value2, 1, i + 1);
            Text value3 = new Text(epis.get(j).getMarca());
            value3.setWrappingWidth(131.13671875);
            value3.setTextAlignment(TextAlignment.CENTER);
            gp_dados2.add(value3, 2, i + 1);
            Label value4 = new Label(Integer.toString(epis.get(j).getQuant()));
            value4.setPrefHeight(36);
            value4.setPrefWidth(70);
            value4.setAlignment(Pos.CENTER);
            gp_dados2.add(value4, 3, i + 1);

            if (epis.get(j).getQuant() <= 10) {
                value4.setStyle("-fx-background-color: #FF6347");
            } else if (epis.get(j).getQuant() > 10 && epis.get(j).getQuant() <= 30) {
                value4.setStyle("-fx-background-color: #FFFF00");
            } else {
                value4.setStyle("-fx-background-color: #98FB98");
            }

            Label value5 = new Label(Integer.toString(epis.get(j).dataToVenc()));
            value5.setPrefHeight(36);
            value5.setPrefWidth(74);
            value5.setAlignment(Pos.CENTER);
            gp_dados2.add(value5, 4, i + 1);

            if (epis.get(j).dataToVenc() <= 10) {
                value5.setStyle("-fx-background-color: #FF6347");
            } else if (epis.get(j).dataToVenc() > 10 && epis.get(j).dataToVenc() <= 30) {
                value5.setStyle("-fx-background-color: #FFFF00");
            } else {
                value5.setStyle("-fx-background-color: #98FB98");
            }

            Button bt = new Button("Editar");
            bt.setPrefHeight(25);
            bt.setPrefWidth(65);
            bt.setStyle("-fx-text-fill: white; -fx-background-color: #337AB7;");
            bt.setId("bt_editar_" + i);
            bt.setOnAction((event) -> {
                editarEpi(event);
            });
            gp_dados2.add(bt, 5, i + 1);

        }
    }

    private void returnData() {
        clearGrid();
        int index = entregas.size() > 10 ? 10 : entregas.size();
        int j = actualPage * 10 - 10;

        if (j > entregas.size()) {
            j -= entregas.size();
        }

        for (int i = 0; i < index; i++, j++) {
            Text value1 = new Text();
            value1.setText(formatData(entregas.get(j).getData_entrega()));
            value1.setWrappingWidth(81.13671875);
            value1.setTextAlignment(TextAlignment.CENTER);
            gp_dados.add(value1, 0, i + 1);
            Text value2 = new Text();
            value2.setText(entregas.get(j).getEpi());
            value2.setWrappingWidth(109.13671875);
            value2.setTextAlignment(TextAlignment.CENTER);
            gp_dados.add(value2, 1, i + 1);
            Text value3 = new Text(entregas.get(j).getFunc());
            value3.setWrappingWidth(98.13671875);
            value3.setTextAlignment(TextAlignment.CENTER);
            gp_dados.add(value3, 2, i + 1);
            Text value4 = new Text(Integer.toString(entregas.get(j).getVida_util()));
            value4.setWrappingWidth(42.13671875);
            value4.setTextAlignment(TextAlignment.CENTER);
            gp_dados.add(value4, 3, i + 1);
            Text value5 = new Text(Integer.toString(entregas.get(j).getQuant()));
            value5.setWrappingWidth(37.13671875);
            value5.setTextAlignment(TextAlignment.CENTER);
            gp_dados.add(value5, 4, i + 1);
            Label value6 = new Label(Integer.toString(entregas.get(j).dataFaltante()));
            value6.setPrefHeight(36);
            value6.setPrefWidth(70);
            value6.setAlignment(Pos.CENTER);
            value6.setStyle("-fx-border-color: black");

            if (entregas.get(j).dataFaltante() <= 10) {
                value6.setStyle("-fx-background-color: #FF6347");
            } else if (entregas.get(j).dataFaltante() > 10 && entregas.get(j).dataFaltante() <= 30) {
                value6.setStyle("-fx-background-color: #FFFF00");
            } else {
                value6.setStyle("-fx-background-color: #98FB98");
            }

            gp_dados.add(value6, 5, i + 1);

            Label value7 = new Label();
            value7.setPrefHeight(34);
            value7.setPrefWidth(38);
            value7.setAlignment(Pos.CENTER);

            if (entregas.get(j).isConfirmado()) {
                value7.setStyle("-fx-text-fill: #98FB98");
                value7.setText("✔");
            } else {
                value7.setStyle("-fx-text-fill: #FF6347");
                value7.setText("❌");
            }

            gp_dados.add(value7, 6, i + 1);

            Button bt = new Button("Devolver");
            bt.setPrefHeight(25);
            bt.setPrefWidth(65);
            bt.setStyle("-fx-text-fill: white; -fx-background-color: #337AB7;");
            bt.setId("bt_devolver_" + i);
            bt.setOnAction((event) -> {
                devolverEntrega(event);
            });
            gp_dados.add(bt, 7, i + 1);
        }
    }

    public void devolverEntrega(ActionEvent e) {
        Button a = (Button) e.getSource();
        String value = a.getId();
        value = value.replace("bt_devolver_", "");
        int index = Integer.parseInt(value);
        DevolverGestorController controller = (DevolverGestorController) App.changeMainScreenData("DevolverGestor");
        controller.setData(entregas.get(index));
    }

    public void editarEpi(ActionEvent e) {
        Button a = (Button) e.getSource();
        String value = a.getId();
        value = value.replace("bt_editar_", "");
        int index = Integer.parseInt(value);
        EpiGestorController controller = (EpiGestorController) App.changeMainScreenData("EpiGestor");
        controller.setEpi(epis.get(index));
    }

    public void nextPage() {
        if (actualPage < pages) {
            actualPage++;
            lb_pag.setText("Página: " + actualPage + " - " + pages);
            returnData();
        }
    }

    public void backPage() {
        if (actualPage > 1) {
            actualPage--;
            lb_pag.setText("Página: " + actualPage + " - " + pages);
            returnData();
        }
    }

    public void nextPage2() {
        if (actualPage2 < pages2) {
            actualPage2++;
            lb_pag2.setText("Página: " + actualPage2 + " - " + pages2);
            returnData2();
        }
    }

    public void backPage2() {
        if (actualPage2 > 1) {
            actualPage2--;
            lb_pag2.setText("Página: " + actualPage2 + " - " + pages2);
            returnData2();
        }
    }

}
