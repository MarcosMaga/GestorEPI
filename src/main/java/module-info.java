module com.mycompany.gestaoepi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires javax.mail;

    opens com.mycompany.gestaoepi to javafx.fxml;
    opens com.mycompany.gestaoepi.controllers to javafx.fxml;
    exports com.mycompany.gestaoepi;
    exports com.mycompany.gestaoepi.dao;
    exports com.mycompany.gestaoepi.models;
    exports com.mycompany.gestaoepi.helpers;
    exports com.mycompany.gestaoepi.controllers;
}
