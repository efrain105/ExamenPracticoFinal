module org.cardona.estructuras.examenpracticofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires annotations;

    exports org.cardona.estructuras.examenpracticofinal;
    exports org.cardona.estructuras.examenpracticofinal.controllers.listasDoblesC;
    exports org.cardona.estructuras.examenpracticofinal.stages;
    opens org.cardona.estructuras.examenpracticofinal.controllers.listasDoblesC to javafx.fxml;


}