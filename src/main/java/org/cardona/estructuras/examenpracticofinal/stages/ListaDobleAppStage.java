package org.cardona.estructuras.examenpracticofinal.stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.cardona.estructuras.examenpracticofinal.controllers.listasDoblesC.ListaDobleAppController;

import java.util.Objects;

public class ListaDobleAppStage extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/examenpracticofinal/pane-examen.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        ListaDobleAppController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);
        primaryStage.setResizable(false); // Make the window non-resizable
        primaryStage.show();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
    }

    public static void lanzarApp() {
        launch();
    }
}