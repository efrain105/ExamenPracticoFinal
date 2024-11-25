package org.cardona.estructuras.examenpracticofinal.controllers.listasDoblesC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.cardona.estructuras.examenpracticofinal.estructura.TDA;
import org.cardona.estructuras.examenpracticofinal.modelo.Cliente;
import org.cardona.estructuras.examenpracticofinal.modelo.Empleado;
import org.cardona.estructuras.examenpracticofinal.modelo.Proveedor;
import org.cardona.estructuras.examenpracticofinal.modelo.Usuario;
import org.cardona.estructuras.examenpracticofinal.modelo.enums.Empresa;
import org.cardona.estructuras.examenpracticofinal.modelo.enums.Producto;
import org.cardona.estructuras.examenpracticofinal.modelo.enums.Puesto;
import org.cardona.estructuras.examenpracticofinal.modelo.enums.TipoCliente;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListaDobleAppController implements Initializable {

    private static TDA<Usuario> arbolListaDoble = null;

    @FXML
    public Pane ventanaprincipal;
    public Label menuTitulo;
    public Button goDestruir;
    public Button goRecorrerLista;
    private Stage stage;
    private boolean ordenLista = true;

    public void setLista(TDA<Usuario> lista) {
        arbolListaDoble = lista;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    private static boolean showConfirmationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(ListaDobleAppController.class.getResource("/style.css")).toExternalForm());
        alert.getDialogPane().getStyleClass().add("custom-alert");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    private void showWarningAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        alert.getDialogPane().getStyleClass().add("custom-alert");
        alert.showAndWait();
    }

    private void showInfoUser(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.setGraphic(null); // Eliminar el icono de alerta
        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        alert.getDialogPane().getStyleClass().add("custom-alert");
        alert.showAndWait();
    }

    @FXML
    public void onGoAgregarUsuario(ActionEvent actionEvent) throws Exception {
        if (arbolListaDoble == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        if (arbolListaDoble == null) {
            return;
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/examenpracticofinal/agregar-usuario.fxml"));
            Parent root = loader.load();
            AgregarUsuarioController agregarUsuarioController = loader.getController();
            agregarUsuarioController.setListaDoble(arbolListaDoble);  // Pass the existing list to the new controller
            agregarUsuarioController.setListaDobleUsuariosAppController(this);  // Pasar la referencia del controlador principal
            Stage stage = new Stage();
            agregarUsuarioController.setStage(stage);
            stage.setScene(new Scene(root));
            stage.setResizable(false); // Make the window non-resizable
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }


    }

    @FXML
    private void OnGoModificarUsuario() throws IOException {
        if (arbolListaDoble == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        if (arbolListaDoble.isEmpty()) {
            showWarningAlert("Lista vacía", "La lista está vacía.");
            return;
        }

        Usuario usuario = validarUsuario();
        if (usuario == null) {
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/examenpracticofinal/modificar-usuario.fxml"));
        Parent root = loader.load();
        ModificarUsuarioController modificarUsuarioController = loader.getController();
        modificarUsuarioController.setListaDoble(arbolListaDoble);
        modificarUsuarioController.setListaDobleAppController(this);
        modificarUsuarioController.setStage(stage);
        modificarUsuarioController.setUsuario(usuario);
        modificarUsuarioController.setUsuarioDelete(usuario);
        Stage stage = new Stage();
        modificarUsuarioController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.setResizable(false); // Make the window non-resizable
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    // Crear lista
    @FXML
    private void crearLista() {

        if (arbolListaDoble != null) {
            showWarningAlert("Lista ya creada", "La lista ya ha sido creada.");
            return;
        }

        arbolListaDoble = new TDA<>();
        //Agregamos unos usuarios de prueba
        arbolListaDoble.insert(new Cliente("6666666666", "cliente2@gmail.com", "Sofia", "Gomez", TipoCliente.VIP.getLabel()));

        //arbolListaDoble.insert(new Empleado("4449876543", "empleado3@gmail.com", "Maria", Puesto.VENDEDOR.getLabel(), 30000));

        arbolListaDoble.insert(new Proveedor("3333333333", "proveedor1@gmail.com", "Carlos", Empresa.EMPRESA1.getLabel(), Producto.COMPUTADORAS.getLabel()));
        arbolListaDoble.insert(new Cliente("5555555555", "cliente1@gmail.com", "Juan", "Perez", TipoCliente.NORMAL.getLabel()));
        arbolListaDoble.insert(new Proveedor("4444444444", "proveedor2@gmail.com", "Laura", Empresa.EMPRESA2.getLabel(), Producto.TELEVISION.getLabel()));
        //arbolListaDoble.insert(new Proveedor("5559876543", "proveedor3@gmail.com", "Pedro", Empresa.EMPRESA3.getLabel(), Producto.ZAPATOS.getLabel()));
        arbolListaDoble.insert(new Empleado("1111111111", "empleado1@gmail.com", "Ana", Puesto.GERENTE.getLabel(), 50000));
        arbolListaDoble.insert(new Empleado("2222222222", "empleado2@gmail.com", "Luis", Puesto.SUBGERENTE.getLabel(), 40000));

        //arbolListaDoble.insert(new Cliente("3339876543", "cliente3@gmail.com", "Miguel", "Lopez", TipoCliente.EMPRESARIAL.getLabel()));

        showWarningAlert("Lista creada", "La lista ha sido creada exitosamente.");
    }


    // Eliminar usuario

    @FXML
    private void onEliminar() {
        if (arbolListaDoble == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }
        if (arbolListaDoble.isEmpty()) {
            showWarningAlert("Lista vacía", "La lista está vacía.");
            return;
        }
        while (true) {
            try {
                Usuario usuario = validarUsuario();
                if (usuario == null) {
                    break;
                }
                String numeroTelefono = usuario.getTelefono();

                // Mostrar confirmación
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmación de eliminación");
                alert.setHeaderText(null);
                alert.setContentText("¿Está seguro de que desea eliminar el usuario con número de teléfono " + numeroTelefono + "?");
                alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
                alert.getDialogPane().getStyleClass().add("custom-alert");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    arbolListaDoble.delete(usuario);
                    showWarningAlert("Usuario eliminado", "El usuario con número de teléfono " + numeroTelefono + " ha sido eliminado.");
                }
                break;
            } catch (IllegalStateException e) {
                // toDo
            }
        }
    }

    @FXML
    private void onVaciarLista() {
        if (arbolListaDoble == null || arbolListaDoble.isEmpty()) {
            showWarningAlert("Lista vacía o no creada", "La lista está vacía o no ha sido creada.");
            return;
        }

        boolean confirmacion = showConfirmationAlert("Confirmación de vaciado", "¿Está seguro de que desea vaciar la lista?");
        if (confirmacion) {
            arbolListaDoble.clear();
            showWarningAlert("Lista vaciada", "La lista ha sido vaciada exitosamente.");
        }
    }
    // Destruir lista

    @FXML
    private void onDestruirLista() {
        if (arbolListaDoble == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        boolean confirmacion = showConfirmationAlert("Confirmación de destrucción", "¿Está seguro de que desea destruir la lista?");
        if (confirmacion) {
            arbolListaDoble = null;
            showWarningAlert("Lista destruida", "La lista ha sido destruida exitosamente.");
        }
    }


    private Usuario validarUsuario() {
        while (true) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Número de teléfono");
            dialog.setHeaderText(null);
            dialog.setContentText("Ingresa el número de teléfono:");
            dialog.getDialogPane().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            dialog.getDialogPane().getStyleClass().add("custom-alert");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent() && result.get().matches("\\d{10}")) {
                String numeroTelefono = result.get();

                Usuario usuario = new Usuario(numeroTelefono);
                boolean encontrado = arbolListaDoble.exists(usuario);

                if (encontrado) {
                    usuario = arbolListaDoble.obtain(usuario);
                    if (usuario instanceof Cliente) {
                        return (Cliente) usuario;
                    } else if (usuario instanceof Empleado) {
                        return (Empleado) usuario;
                    } else if (usuario instanceof Proveedor) {
                        return (Proveedor) usuario;
                    } else {
                        showWarningAlert("Tipo de usuario desconocido", "El usuario encontrado no es de un tipo conocido.");
                        return null;
                    }
                } else {
                    showWarningAlert("Usuario no encontrado", "No se encontró un usuario con ese número de teléfono.");
                    return null;
                }
            } else if (!result.isPresent()) {
                // Si se presiona cancelar retorna null y sales del método
                return null;
            } else {
                showWarningAlert("Entrada no válida", "Por favor, ingrese un número de teléfono válido de 10 dígitos.");
            }
        }
    }


    public void onGoBuscar(ActionEvent actionEvent) {
        if (arbolListaDoble == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }
        if (arbolListaDoble.isEmpty()) {
            showWarningAlert("Lista vacía", "La lista está vacía.");
            return;
        }
        boolean flag = false;
        if(!ordenLista){
            arbolListaDoble.revertTree();
            flag = true;
        }
        while (true) {
            try {
                Usuario usuario = validarUsuario();
                StringBuilder mensaje = new StringBuilder();
                String tipoUsuario = usuario instanceof Cliente ? "Cliente" : usuario instanceof Empleado ? "Empleado" : "Proveedor";
                if (usuario != null) {
                    mensaje.append("El " + tipoUsuario + " con número de teléfono ").append(usuario.getTelefono()).append(" ha sido encontrado.\n\n");
                    mensaje.append("Nombre: ").append(usuario.getNombre()).append("\n");
                    mensaje.append("Correo: ").append(usuario.getCorreo()).append("\n");

                    if (usuario instanceof Cliente) {
                        Cliente cliente = (Cliente) usuario;
                        mensaje.append("Dirección: ").append(cliente.getDireccion()).append("\n");
                        mensaje.append("Tipo de Cliente: ").append(cliente.getTipoCliente()).append("\n");
                    } else if (usuario instanceof Empleado) {
                        Empleado empleado = (Empleado) usuario;
                        mensaje.append("Puesto: ").append(empleado.getPuesto()).append("\n");
                        mensaje.append("Salario: ").append(empleado.getSalario()).append("\n");
                    } else if (usuario instanceof Proveedor) {
                        Proveedor proveedor = (Proveedor) usuario;
                        mensaje.append("Empresa: ").append(proveedor.getEmpresa()).append("\n");
                        mensaje.append("Producto: ").append(proveedor.getProducto()).append("\n");
                    }

                    showInfoUser("Usuario encontrado", mensaje.toString());

                }
                break;
            } catch (IllegalStateException e) {
                // toDo
            }
        }
        if(flag){
            arbolListaDoble.revertTree();
        }
    }

    public void onInvertirLista(ActionEvent actionEvent) {
        if (arbolListaDoble == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }
        if (arbolListaDoble.isEmpty()) {
            showWarningAlert("Lista vacía", "La lista está vacía.");
            return;
        }
        arbolListaDoble.invertTree();
        this.ordenLista = !this.ordenLista;
        showWarningAlert("Lista invertida", "La lista ha sido invertida exitosamente.");
    }


    public void onRecorrerLista(ActionEvent actionEvent) throws IOException {

        if (arbolListaDoble == null) {
            showWarningAlert("Lista no creada", "La lista no ha sido creada.");
            return;
        }

        if (arbolListaDoble.isEmpty()) {
            showWarningAlert("Lista vacía", "La lista está vacía.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cardona/estructuras/examenpracticofinal/recorrer-nodos.fxml"));
        Parent root = loader.load();
        RecorrerNodosController recorrerNodosController = loader.getController();
        recorrerNodosController.setListaDobleArbol(arbolListaDoble);
        recorrerNodosController.setOrdenLista(ordenLista);
        recorrerNodosController.setListaDobleAppController(this);
        recorrerNodosController.setStage(stage);

        Stage stage = new Stage();
        recorrerNodosController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.setResizable(false); // Make the window non-resizable
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
