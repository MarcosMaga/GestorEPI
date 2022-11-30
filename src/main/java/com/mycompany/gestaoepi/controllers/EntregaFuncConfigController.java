/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gestaoepi.controllers;

import com.mycompany.gestaoepi.App;
import com.mycompany.gestaoepi.dao.EntregaDao;
import com.mycompany.gestaoepi.dao.EpiDao;
import com.mycompany.gestaoepi.dao.FuncDao;
import com.mycompany.gestaoepi.helpers.ScreenHelpers;
import com.mycompany.gestaoepi.helpers.SendEmail;
import com.mycompany.gestaoepi.models.Epi;
import com.mycompany.gestaoepi.models.Funcionario;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class EntregaFuncConfigController extends ScreenHelpers implements Initializable {

    /**
     * Initializes the controller class.
     */
    private List<Epi> epis;

    private List<Funcionario> funcionarios;

    @FXML
    private DatePicker dp_entrega;

    @FXML
    private ComboBox<Epi> cb_epi;

    @FXML
    private ComboBox<Funcionario> cb_func;

    @FXML
    private ComboBox<Integer> cb_quant;

    @FXML
    private Label lb_status;

    @FXML
    private Button bt_salvar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EpiDao daoEpi = new EpiDao();
        epis = daoEpi.SelectEpi(App.usuario.getId());
        ObservableList<Epi> obsEpi = FXCollections.observableArrayList(epis);
        cb_epi.setItems(obsEpi);
        FuncDao daoFunc = new FuncDao();
        funcionarios = daoFunc.SelectFunc(App.usuario.getId());
        ObservableList<Funcionario> obsFunc = FXCollections.observableArrayList(funcionarios);
        cb_func.setItems(obsFunc);
        cb_quant.setItems(setNumberValues(1, 100));
    }

    public void saveData() {
        lb_status.setVisible(true);

        if (verifyFields()) {
            EntregaDao dao = new EntregaDao();
            Epi epi = cb_epi.getValue();
            Funcionario func = cb_func.getValue();

            if (cb_quant.getValue() <= epi.getQuant()) {
                if (epi.isValid(dp_entrega.getValue(), cb_quant.getValue())) {
                    String code = createCode(epi.getNome().charAt(0), func.getNome().charAt(0));
                    try {
                        try {
                            lb_status.setStyle("-fx-background-color: #DFF0D8;");
                            lb_status.setStyle("-fx-border-color: red;");
                            lb_status.setTextFill(Color.YELLOW);
                            lb_status.setText("Enviando Email com o código para " + func.getNome());
                        } finally {
                            dao.insertEntregaFunc(App.usuario.getId(), func.getId(), epi.getId(), dp_entrega.getValue().toString(), cb_quant.getValue().toString(), code);
                            SendEmail email = new SendEmail();
                            email.sendEmail(func.getEmail(), code, func.getNome());
                        }

                        lb_status.setStyle("-fx-background-color: #DFF0D8;");
                        lb_status.setStyle("-fx-border-color: green;");
                        lb_status.setTextFill(Color.GREEN);
                        lb_status.setText("Entrega de " + epi.getNome() + " para " + func.getNome() + " feita com sucesso.");
                        dp_entrega.setValue(null);
                        cb_epi.setValue(null);
                        cb_func.setValue(null);
                        cb_quant.setValue(null);
                    } catch (Exception ex) {
                        lb_status.setStyle("-fx-background-color: #F2DEDE;");
                        lb_status.setStyle("-fx-border-color: red;");
                        lb_status.setTextFill(Color.RED);
                        lb_status.setText("Entrega de " + epi.getNome() + " para " + func.getNome() + "falhou.");
                    }
                }
                else{
                    Alert alert = new Alert(AlertType.ERROR, "Este EPI estará vencido antes da data prevista de devolução.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR, "Estoque insuficiente de EPI");
                alert.showAndWait();
            }
        } else {
            lb_status.setStyle("-fx-background-color: ##F2DEDE;");
            lb_status.setStyle("-fx-border-color: red;");
            lb_status.setTextFill(Color.RED);
            lb_status.setText("Campos vazios ou invalidos.");
        }

    }

    private boolean verifyFields() {
        if (dp_entrega.getValue() == null || cb_epi.getValue() == null || cb_func.getValue() == null || cb_quant.getValue() == null) {
            return false;
        }

        return true;
    }

}
