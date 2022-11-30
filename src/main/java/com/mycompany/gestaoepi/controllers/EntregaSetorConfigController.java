/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestaoepi.controllers;

import com.mycompany.gestaoepi.App;
import com.mycompany.gestaoepi.dao.EntregaDao;
import com.mycompany.gestaoepi.dao.EpiDao;
import com.mycompany.gestaoepi.dao.FuncDao;
import com.mycompany.gestaoepi.dao.SetorDao;
import com.mycompany.gestaoepi.helpers.ScreenHelpers;
import com.mycompany.gestaoepi.helpers.SendEmail;
import com.mycompany.gestaoepi.models.Epi;
import com.mycompany.gestaoepi.models.Funcionario;
import com.mycompany.gestaoepi.models.Setor;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class EntregaSetorConfigController extends ScreenHelpers implements Initializable {

    /**
     * Initializes the controller class.
     */
    private List<Epi> epis;
    private List<Setor> setores;

    @FXML
    private DatePicker dp_entrega;

    @FXML
    private ComboBox<Epi> cb_epi;

    @FXML
    private ComboBox<Setor> cb_setor;

    @FXML
    private ComboBox<Integer> cb_quant;

    @FXML
    private Label lb_status;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EpiDao daoEpi = new EpiDao();
        epis = daoEpi.SelectEpi(App.usuario.getId());
        ObservableList<Epi> obsEpi = FXCollections.observableArrayList(epis);
        cb_epi.setItems(obsEpi);
        SetorDao daoSetor = new SetorDao();
        setores = daoSetor.SelectSetor(App.usuario.getId());
        ObservableList<Setor> obsSetor = FXCollections.observableArrayList(setores);
        cb_setor.setItems(obsSetor);
        cb_quant.setItems(setNumberValues(1, 100));
    }

    public void saveData() {
        lb_status.setVisible(true);
        if (verifyFields()) {
            try {
                FuncDao daoFunc = new FuncDao();
                EntregaDao daoEntrega = new EntregaDao();
                List<Funcionario> funcSetor = daoFunc.SelectFuncBySetor(App.usuario.getId(), cb_setor.getValue().getId());
                Epi epi = cb_epi.getValue();
                int numTotalEpi = cb_quant.getValue() * funcSetor.size();

                if (numTotalEpi <= epi.getQuant()) {
                    if (epi.isValid(dp_entrega.getValue(), cb_quant.getValue())) {
                        for (Funcionario a : funcSetor) {
                            String code = createCode(epi.getNome().charAt(0), a.getNome().charAt(0));
                            daoEntrega.insertEntregaFunc(App.usuario.getId(), a.getId(), epi.getId(), dp_entrega.getValue().toString(), cb_quant.getValue().toString(), code);
                            SendEmail email = new SendEmail();
                            email.sendEmail(a.getEmail(), code, a.getNome());
                        }

                        lb_status.setStyle("-fx-background-color: #DFF0D8;");
                        lb_status.setStyle("-fx-border-color: green;");
                        lb_status.setTextFill(Color.GREEN);
                        lb_status.setText("Entrega de " + epi.getNome() + " para o setor " + cb_setor.getValue().getNome() + "feita com sucesso.");
                        dp_entrega.setValue(null);
                        cb_epi.setValue(null);
                        cb_setor.setValue(null);
                        cb_quant.setValue(null);
                    } else{
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Este EPI estará vencido antes da data prevista de devolução.");
                        alert.showAndWait();
                    }

                } else {
                    lb_status.setStyle("-fx-background-color: ##F2DEDE;");
                    lb_status.setStyle("-fx-border-color: red;");
                    lb_status.setTextFill(Color.RED);
                    lb_status.setText("Estoque insuficiente de EPI.");
                }

            } catch (Exception ex) {
                lb_status.setStyle("-fx-background-color: ##F2DEDE;");
                lb_status.setStyle("-fx-border-color: red;");
                lb_status.setTextFill(Color.RED);
                lb_status.setText("Entrega de " + cb_epi.getValue().getNome() + " para o setor " + cb_setor.getValue().getNome() + "falhou.");
            }
        } else {
            lb_status.setStyle("-fx-background-color: ##F2DEDE;");
            lb_status.setStyle("-fx-border-color: red;");
            lb_status.setTextFill(Color.RED);
            lb_status.setText("Campos vazios ou invalidos.");
        }
    }

    private boolean verifyFields() {
        if (dp_entrega.getValue() == null || cb_epi.getValue() == null || cb_setor.getValue() == null || cb_quant.getValue() == null) {
            return false;
        }

        return true;
    }

}
