package org.cardona.estructuras.examenpracticofinal.controllers.listasDoblesC;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.cardona.estructuras.examenpracticofinal.estructura.TDA;
import org.cardona.estructuras.examenpracticofinal.modelo.Cliente;
import org.cardona.estructuras.examenpracticofinal.modelo.Empleado;
import org.cardona.estructuras.examenpracticofinal.modelo.Proveedor;
import org.cardona.estructuras.examenpracticofinal.modelo.Usuario;
import org.cardona.estructuras.examenpracticofinal.modelo.enums.Empresa;
import org.cardona.estructuras.examenpracticofinal.modelo.enums.Producto;
import org.cardona.estructuras.examenpracticofinal.modelo.enums.Puesto;
import org.cardona.estructuras.examenpracticofinal.modelo.enums.TipoCliente;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarUsuarioController implements Initializable {

    //Usuario
    public TextField telefono;
    public TextField correo;
    public TextField nombre;

    //Cliente
    public TextField direccion;
    public ComboBox<TipoCliente> tipoCliente;

    //Empleado
    public ComboBox<Puesto> puesto;
    public TextField salario;

    //Proveedor
    public ComboBox<Empresa> empresa;
    public ComboBox<Producto> producto;


    public Label menuTitulo;
    public Pane ventanaprincipal;
    public Button guardar;
    public Button regresar;
    public StackPane camposAdicionales;
    public VBox clienteContainer;
    public VBox empleadoContainer;
    public VBox proveedorContainer;
    private Stage stage;

    @FXML
    private ComboBox<String> tipoUsuario;

    private TDA<Usuario> arbolUsuarios;

    private ListaDobleAppController listaDobleUsuariosAppController;


    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }


    public void setListaDoble(TDA<Usuario> lista) {
        this.arbolUsuarios = lista;
    }

    public void setListaDobleUsuariosAppController(ListaDobleAppController listaDobleAppController) {
        this.listaDobleUsuariosAppController = listaDobleAppController;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ocultarCamposAdicionales();
        inicializarComboBoxes();
    }

    private void inicializarComboBoxes() {

        tipoCliente.setItems(FXCollections.observableArrayList(TipoCliente.values()));
        puesto.setItems(FXCollections.observableArrayList(Puesto.values()));
        empresa.setItems(FXCollections.observableArrayList(Empresa.values()));
        producto.setItems(FXCollections.observableArrayList(Producto.values()));

        tipoCliente.setValue(TipoCliente.NORMAL);
        puesto.setValue(Puesto.GERENTE);
        empresa.setValue(Empresa.EMPRESA1);
        producto.setValue(Producto.ELECTRODOMESTICOS);

    }

    public void onGuardar(ActionEvent actionEvent) throws Exception {
        if (tipoUsuario.getValue() != null) {
            if ("Cliente".equals(tipoUsuario.getValue())) {
                guardarCliente();
            } else if ("Empleado".equals(tipoUsuario.getValue())) {
                guardarEmpleado();
            } else if ("Proveedor".equals(tipoUsuario.getValue())) {
                guardarProveedor();
            }
        }
    }

    private void guardarCliente() {
        String telefonoUsuario = telefono.getText();
        String correoUsuario = correo.getText();
        String nombreUsuario = nombre.getText();
        String direcccionCliente = direccion.getText();
        String tipoClienteCliente = tipoCliente.getValue().name();

        if (!telefonoUsuario.isEmpty() && !correoUsuario.isEmpty() && !tipoClienteCliente.isEmpty() && !nombreUsuario.isEmpty() && !direcccionCliente.isEmpty()) {
            if (validarTelefono(telefonoUsuario) == 0 || !validarCorreo(correoUsuario)) {
                return;
            }


            Usuario nuevoCliente = new Cliente(telefonoUsuario, correoUsuario, nombreUsuario, direcccionCliente, tipoClienteCliente);

            if (arbolUsuarios != null && !arbolUsuarios.exists(nuevoCliente)) {
                arbolUsuarios.insert(nuevoCliente);
                showWarningAlert("Cliente guardado", "El cliente ha sido guardado exitosamente.");
                stage.close();
            } else {
                showWarningAlert("Cliente ya existe", "El cliente ya existe en la base de datos.");
            }


            if (listaDobleUsuariosAppController != null) {

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacíos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, llene todos los campos antes de guardar.");
            alert.showAndWait();
        }
    }

    private void guardarEmpleado() {
        String telefonoUsuario = telefono.getText();
        String correoUsuario = correo.getText();
        String nombreUsuario = nombre.getText();
        String puestoEmpleado = puesto.getValue().name();
        String salarioEmpleado = salario.getText();

        if (!telefonoUsuario.isEmpty() && !correoUsuario.isEmpty() && !puestoEmpleado.isEmpty() && !nombreUsuario.isEmpty() && !salarioEmpleado.isEmpty()) {
            if (!validarDouble(salarioEmpleado) || validarTelefono(telefonoUsuario) == 0 || !validarCorreo(correoUsuario)) {
                return;
            }

            Usuario nuevoEmpleado = new Empleado(telefonoUsuario, correoUsuario, nombreUsuario, puestoEmpleado, Double.parseDouble(salarioEmpleado));

            if (arbolUsuarios != null && !arbolUsuarios.exists(nuevoEmpleado)) {
                arbolUsuarios.insert(nuevoEmpleado);
                showWarningAlert("Empleado guardado", "El empleado ha sido guardado exitosamente.");
            } else {
                showWarningAlert("Empleado ya existe", "El empleado ya existe en la base de datos.");
            }
            stage.close();

            if (listaDobleUsuariosAppController != null) {

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacíos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, llene todos los campos antes de guardar.");
            alert.showAndWait();
        }
    }

    private void guardarProveedor() {
        String telefonoUsuario = telefono.getText();
        String correoUsuario = correo.getText();
        String nombreUsuario = nombre.getText();
        String empresaProveedor = empresa.getValue().name();
        String productoProveedor = producto.getValue().name();

        if (!telefonoUsuario.isEmpty() && !correoUsuario.isEmpty() && !empresaProveedor.isEmpty() && !nombreUsuario.isEmpty() && !productoProveedor.isEmpty()) {
            if (validarTelefono(telefonoUsuario) == 0 || !validarCorreo(correoUsuario)) {
                return;
            }

            Usuario nuevoProveedor = new Proveedor(telefonoUsuario, correoUsuario, nombreUsuario, empresaProveedor, productoProveedor);

            if (arbolUsuarios != null && !arbolUsuarios.exists(nuevoProveedor)) {
                arbolUsuarios.insert(nuevoProveedor);
                showWarningAlert("Proveedor guardado", "El proveedor ha sido guardado exitosamente.");
                stage.close();
            } else {
                showWarningAlert("Proveedor ya existe", "El proveedor ya existe en la base de datos.");
            }


        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacíos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, llene todos los campos antes de guardar.");
            alert.showAndWait();
        }
    }


    private int validarInt(String numero) {
        try {
            return Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            showWarningAlert("Numero invalido", "Por favor, ingrese un numero entero.");
        }
        return 0;
    }

    private boolean validarDouble(String numero) {
        try {
            Double.parseDouble(numero);
            return true;
        } catch (NumberFormatException e) {
            showWarningAlert("Número inválido", "Por favor, ingrese un número decimal.");
            return false;
        }
    }

    private int validarTelefono(String telefono) {
        if (telefono.matches("\\d{10}")) {
            return 1;
        } else {
            showWarningAlert("Número telefónico inválido", "El número telefónico debe tener exactamente 10 dígitos.");
            return 0;
        }
    }

    private boolean validarCorreo(String correo) {
        if (correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            return true;
        } else {
            showWarningAlert("Correo electrónico inválido", "Correo electrónico no válido.");
            return false;
        }
    }

    private void showWarningAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("custom-alert");
        alert.showAndWait();
    }


    public void onRegresar(ActionEvent actionEvent) throws Exception {
        stage.close();
    }


    @FXML
    private void onTipoUsuarioSeleccionado() {
        String seleccion = tipoUsuario.getValue().toString();
        clienteContainer.setVisible("Cliente".equals(seleccion));
        empleadoContainer.setVisible("Empleado".equals(seleccion));
        proveedorContainer.setVisible("Proveedor".equals(seleccion));
    }


    private void ocultarCamposAdicionales() {
        tipoUsuario.setValue("Cliente");
        clienteContainer.setVisible(true);
        empleadoContainer.setVisible(false);
        proveedorContainer.setVisible(false);
    }


}
