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
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ModificarUsuarioController implements Initializable {
    //Usuario
    @FXML
    //Usuario
    public TextField telefono;
    public TextField correo;
    public TextField nombre;

    //Cliente
    public TextField direccion;
    public ComboBox<String> tipoCliente;

    //Empleado
    public ComboBox<String> puesto;
    public TextField salario;

    //Proveedor
    public ComboBox<String> empresa;
    public ComboBox<String> producto;


    public Label menuTitulo;
    public Pane ventanaprincipal;
    public Button regresar;
    public TextArea outputArea;
    public Button modificar;
    public VBox voxPos;
    public StackPane camposAdicionales;
    public VBox clienteContainer;
    public VBox empleadoContainer;
    public VBox proveedorContainer;
    public ComboBox<String> tipoUsuario;
    private Stage stage;
    private Usuario usuarioDelete;


    public void setUsuarioDelete(Usuario usuario) {
        this.usuarioDelete = usuario;
    }

    private ListaDobleAppController listaDobleAppController;
    private TDA<Usuario> listaDoble;


    public void setListaDobleAppController(ListaDobleAppController listaDobleAppController) {
        this.listaDobleAppController = listaDobleAppController;

    }

    public void setListaDoble(TDA<Usuario> lista) {
        this.listaDoble = lista;  // Establecer la lista compartida es importante mencionar this.lista para referirse a la lista de la clase
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializarComboBoxes();
    }

    private void inicializarComboBoxes() {

        tipoCliente.setItems(FXCollections.observableArrayList(
                Arrays.stream(TipoCliente.values()).map(Enum::name).collect(Collectors.toList())
        ));
        puesto.setItems(FXCollections.observableArrayList(
                Arrays.stream(Puesto.values()).map(Enum::name).collect(Collectors.toList())
        ));
        empresa.setItems(FXCollections.observableArrayList(
                Arrays.stream(Empresa.values()).map(Enum::name).collect(Collectors.toList())
        ));
        producto.setItems(FXCollections.observableArrayList(
                Arrays.stream(Producto.values()).map(Enum::name).collect(Collectors.toList())
        ));

        tipoCliente.setValue(TipoCliente.NORMAL.name());
        puesto.setValue(Puesto.GERENTE.name());
        empresa.setValue(Empresa.EMPRESA1.name());
        producto.setValue(Producto.ELECTRODOMESTICOS.name());

    }


    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }


    public void onRegresar(ActionEvent actionEvent) throws Exception {
        stage.close();
    }


    public void onModificar(ActionEvent actionEvent) {
        String tipo = tipoUsuario.getValue();
        if ("Cliente".equals(tipo)) {
            modificarCliente();
        } else if ("Empleado".equals(tipo)) {
            modificarEmpleado();
        } else if ("Proveedor".equals(tipo)) {
            modificarProveedor();
        }
    }

    private void modificarCliente() {
        String telefonoCliente = telefono.getText();
        String correoCliente = correo.getText();
        String nombreCliente = nombre.getText();
        String direccionCliente = direccion.getText();
        String tipoClicneteCliente = tipoCliente.getValue();

        if (!telefonoCliente.isEmpty() && !correoCliente.isEmpty() && !nombreCliente.isEmpty() && !direccionCliente.isEmpty() && !tipoClicneteCliente.isEmpty()) {
            try {
                if (validarTelefono(telefonoCliente) == 0 || validarCorreo(correoCliente).isEmpty()) {
                    return;
                }


                Usuario usuarioModificado = new Cliente(telefonoCliente, correoCliente, nombreCliente, direccionCliente, tipoClicneteCliente);


                // Modificar el usuario
                if (listaDoble != null) {
                    listaDoble.delete(usuarioDelete);
                    listaDoble.insert(usuarioModificado);
                }
                showWarningAlert("Cliente modificado", "El cliente ha sido modificado exitosamente.");

                stage.close();

                if (listaDobleAppController != null) {

                }

            } catch (NumberFormatException e) {
                showWarningAlert("Número inválido", "Por favor, ingrese un número entero.");
            } catch (IndexOutOfBoundsException e) {
                showWarningAlert("Posición no válida", "La posición no puede ser menor a 1 o mayor al tamaño de la lista.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            showWarningAlert("Campos vacíos", "Por favor, llene todos los campos antes de guardar.");
        }
    }

    private void modificarEmpleado() {
        String telefonoEmpleado = telefono.getText();
        String correoEmpleado = correo.getText();
        String nombreEmpleado = nombre.getText();
        String puestoEmpleado = puesto.getValue();
        String salarioEmpleado = salario.getText();

        if (!telefonoEmpleado.isEmpty() && !correoEmpleado.isEmpty() && !nombreEmpleado.isEmpty() && !puestoEmpleado.isEmpty() && !salarioEmpleado.isEmpty()) {
            try {
                if (validarTelefono(telefonoEmpleado) == 0 || validarCorreo(correoEmpleado).isEmpty() || !validarDouble(salarioEmpleado)) {
                    return;
                }

                Usuario usuarioModificado = new Empleado(telefonoEmpleado, correoEmpleado, nombreEmpleado, puestoEmpleado, Double.parseDouble(salarioEmpleado));

                // Modificar el usuario
                if (listaDoble != null) {
                    listaDoble.delete(usuarioDelete);
                    listaDoble.insert(usuarioModificado);
                }
                showWarningAlert("Empleado modificado", "El empleado ha sido modificado exitosamente.");

                stage.close();

                if (listaDobleAppController != null) {
                    // Actualizar la lista en el controlador principal si es necesario
                }

            } catch (NumberFormatException e) {
                showWarningAlert("Número inválido", "Por favor, ingrese un número válido.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            showWarningAlert("Campos vacíos", "Por favor, llene todos los campos antes de guardar.");
        }
    }

    private void modificarProveedor() {
        String telefonoProveedor = telefono.getText();
        String correoProveedor = correo.getText();
        String nombreProveedor = nombre.getText();
        String empresaProveedor = empresa.getValue();
        String productoProveedor = producto.getValue();

        if (!telefonoProveedor.isEmpty() && !correoProveedor.isEmpty() && !nombreProveedor.isEmpty() && !empresaProveedor.isEmpty() && !productoProveedor.isEmpty()) {
            try {
                if (validarTelefono(telefonoProveedor) == 0 || validarCorreo(correoProveedor).isEmpty()) {
                    return;
                }

                Usuario usuarioModificado = new Proveedor(telefonoProveedor, correoProveedor, nombreProveedor, empresaProveedor, productoProveedor);

                // Modificar el usuario
                if (listaDoble != null) {
                    listaDoble.delete(usuarioDelete);
                    listaDoble.insert(usuarioModificado);
                }
                showWarningAlert("Proveedor modificado", "El proveedor ha sido modificado exitosamente.");

                stage.close();

                if (listaDobleAppController != null) {
                    // Actualizar la lista en el controlador principal si es necesario
                }

            } catch (NumberFormatException e) {
                showWarningAlert("Número inválido", "Por favor, ingrese un número válido.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            showWarningAlert("Campos vacíos", "Por favor, llene todos los campos antes de guardar.");
        }
    }


    public void setUsuario(Usuario usuario) {

        if (usuario != null) {
            if (usuario instanceof Cliente cliente) {
                telefono.setText(cliente.getTelefono());
                telefono.setDisable(true);
                clienteContainer.setVisible(true);
                tipoUsuario.setVisible(true);
                tipoUsuario.setValue("Cliente");
                tipoUsuario.setDisable(true);
                direccion.setText(cliente.getDireccion());
                tipoCliente.setValue(cliente.getTipoCliente());
            } else if (usuario instanceof Empleado empleado) {
                empleadoContainer.setVisible(true);
                telefono.setText(empleado.getTelefono());
                telefono.setDisable(true);
                tipoUsuario.setValue("Empleado");
                tipoUsuario.setDisable(true);
                puesto.setValue(empleado.getPuesto());
                salario.setText(String.valueOf(empleado.getSalario()));
            } else if (usuario instanceof Proveedor proveedor) {
                proveedorContainer.setVisible(true);
                telefono.setText(proveedor.getTelefono());
                telefono.setDisable(true);
                tipoUsuario.setValue("Proveedor");
                tipoUsuario.setDisable(true);
                empresa.setValue(proveedor.getEmpresa());
                producto.setValue(proveedor.getProducto());
            }
            tipoUsuario.setDisable(true); // Deshabilitar el campo de slot
            telefono.setText(usuario.getTelefono());
            correo.setText(usuario.getCorreo());
            nombre.setText(String.valueOf(usuario.getNombre()));
        }
    }


    private Usuario validarUsuario() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Número de teléfono");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingresa el número de teléfono:");
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        dialog.getDialogPane().getStyleClass().add("custom-alert");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && result.get().matches("\\d{10}")) {
            String numeroTelefono = result.get();

            Usuario usuario = new Usuario(numeroTelefono, "", "");
            boolean encontrado = listaDoble.exists(usuario);
            if (encontrado) {
                usuario = listaDoble.obtain(usuario);
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
            return null;
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
            showWarningAlert("Número inválido", "Por favor, ingrese un número válido.");
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

    private String validarCorreo(String correo) {
        if (correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            return correo;
        } else {
            showWarningAlert("Correo electrónico inválido", "Correo electrónico no válido.");
            return "";
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

    @FXML
    private void onTipoUsuarioSeleccionado() {
        String seleccion = tipoUsuario.getValue().toString();
        clienteContainer.setVisible("Cliente".equals(seleccion));
        empleadoContainer.setVisible("Empleado".equals(seleccion));
        proveedorContainer.setVisible("Proveedor".equals(seleccion));
    }
}
