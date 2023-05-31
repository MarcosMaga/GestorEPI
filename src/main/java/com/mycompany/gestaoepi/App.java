package com.mycompany.gestaoepi;

import com.mycompany.gestaoepi.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    private static String nameScene;
    private static ScrollPane dashScreen;
    
    public static User usuario;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Login"));
        stage.setTitle("Gestão EPI");
        stage.setScene(scene);
        stage.show();
        App.stage = stage;
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void changeScreen(String fxml){
        try {
            Scene newScene = new Scene(loadFXML(fxml));
            App.stage.setScene(newScene);
            App.scene = newScene;
            App.nameScene = fxml;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void setDashScreen(ScrollPane sp){
        App.dashScreen = sp;
    }
    
    public static Object changeScreenData(String fxml){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
            Scene newScene = new Scene(fxmlLoader.load());
            App.stage.setScene(newScene);
            App.scene = newScene;
            App.nameScene = fxml;
            
            try{
                return fxmlLoader.getController();
            }catch(Exception ex){
                return null;
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public static String HashPassword(String pass){
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            try {
                byte mdPass[] = algorithm.digest(pass.getBytes("UTF8"));
                StringBuilder hex = new StringBuilder();
                
                for(byte b: mdPass)
                    hex.append(String.format("%02X", 0xFF & b));
                
                return hex.toString();
                
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public static void changeMainScreen(String fxml){
        try {
            Pane pn = (Pane) loadFXML(fxml);
            App.dashScreen.setContent(pn);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ScrollPane getDashScreen(){
        return App.dashScreen;
    }
    
    public static Object changeMainScreenData(String fxml){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
            Pane pn = (Pane) fxmlLoader.load();
            App.dashScreen.setContent(pn);
            return fxmlLoader.getController();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void closeStage(){
        App.stage.close();
    }
}