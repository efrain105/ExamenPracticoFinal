package org.cardona.estructuras.examenpracticofinal.stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.cardona.estructuras.examenpracticofinal.controllers.listasDoblesC.RecorrerNodosController;

import java.util.Objects;

public class RecorrerNodosStage extends Application {
    private Stage primaryStage;
    private RecorrerNodosController controller;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/examenpracticofinal/recorrer-nodos.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        RecorrerNodosController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
    }



    public static void lanzarApp() {
        launch();
    }
}
