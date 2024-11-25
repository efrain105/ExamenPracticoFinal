package org.cardona.estructuras.examenpracticofinal.controllers.listasDoblesC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.cardona.estructuras.examenpracticofinal.estructura.TDA;
import org.cardona.estructuras.examenpracticofinal.estructura.NodoBTS;
import org.cardona.estructuras.examenpracticofinal.modelo.Usuario;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecorrerNodosController implements Initializable {
    public Button regresar;
    public Label menuTitulo;
    public Button primero;
    public Button siguiente;
    public Button anterior;
    public Button ultimo;
    public Button todos;
    public TextArea outPutArea;
    private Stage stage;
    private TDA<Usuario> listaDobleArbol;  // Referencia al árbol binario de búsqueda
    private ListaDobleAppController listaDobleAppController; // Referencia al controlador de la lista
    private NodoBTS<Usuario> nodoActual; // Nodo actual en el recorrido
    private boolean ordenLista;

    private List<Usuario> listaOrdenada;
    private int indiceActual = -1; // Índice de navegación, -1 indica que no se ha iniciado

    public void inicializarListaOrdenada(boolean ordenNormal) {
        listaOrdenada = listaDobleArbol.toOrderList(ordenNormal);
    }

    public void setOrdenLista(boolean ordenLista) {
        this.ordenLista = ordenLista;
    }

    private NodoBTS<Usuario> obtenerNodoActual() {
        return nodoActual;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicialización si es necesario
    }

    public void setListaDobleAppController(ListaDobleAppController listaDobleAppController) {
        this.listaDobleAppController = listaDobleAppController;
    }

    public void setListaDobleArbol(TDA<Usuario> listaDobleArbol) {
        this.listaDobleArbol = listaDobleArbol;
        inicializarListaOrdenada(ordenLista);
        if (listaDobleArbol != null && !listaDobleArbol.isEmpty()) {
            // Asegurarse de que la lista ordenada esté correctamente inicializada
            listaOrdenada = listaDobleArbol.toOrderList(ordenLista); // Ordena de acuerdo con el flag 'ordenLista'
            if (!listaOrdenada.isEmpty()) {
                // Establecer el primer nodo visible
                nodoActual = listaDobleArbol.getRoot(); // Asegúrate de obtener el nodo raíz correctamente
                indiceActual = 0; // Comienza con el primer índice
                mostrarUsuario(listaOrdenada.get(indiceActual)); // Muestra el primer usuario
            }
        }
    }


    public void onRegresar(ActionEvent actionEvent) {
        stage.close();
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void OnPrimero(ActionEvent actionEvent) {
        if (listaDobleArbol != null && !listaDobleArbol.isEmpty()) {
            NodoBTS<Usuario> primero = listaDobleArbol.getRoot();
            while (primero.getLeft() != null) {
                primero = primero.getLeft();
            }
            nodoActual = primero;
            mostrarNodo(primero);
            indiceActual = 0;
        }
    }

    public void OnUltimo(ActionEvent actionEvent) {
        if (listaDobleArbol != null && !listaDobleArbol.isEmpty()) {
            NodoBTS<Usuario> ultimo = listaDobleArbol.getRoot();
            while (ultimo.getRight() != null) {
                ultimo = ultimo.getRight();
            }
            nodoActual = ultimo;
            mostrarNodo(ultimo);
            indiceActual = listaOrdenada.size() -1;
        }
    }

    @FXML
    public void OnSiguiente(ActionEvent actionEvent) {
        if (listaOrdenada != null && !listaOrdenada.isEmpty()) {
            // Navegar al siguiente índice en la lista
            indiceActual = (indiceActual + 1) % listaOrdenada.size();  // Usa el operador % para volver al principio
            mostrarUsuario(listaOrdenada.get(indiceActual));
        }
    }

    @FXML
    public void OnAnterior(ActionEvent actionEvent) {
        if (listaOrdenada != null && !listaOrdenada.isEmpty()) {
            // Navegar al índice anterior en la lista
            indiceActual = (indiceActual - 1 + listaOrdenada.size()) % listaOrdenada.size();  // Usa el operador % para volver al final
            mostrarUsuario(listaOrdenada.get(indiceActual));
        }
    }





/*
    public void OnSiguiente(ActionEvent actionEvent) {
    System.out.println("Orden lista: " + ordenLista);
        if (listaDobleArbol != null && !listaDobleArbol.isEmpty()) {
            NodoBTS<Usuario> siguiente = obtenerSiguienteNodo();
            if (siguiente != null) {
                mostrarNodo(siguiente);
            }
        }
    }

    public void OnAnterior(ActionEvent actionEvent) {
        if (listaDobleArbol != null && !listaDobleArbol.isEmpty()) {
            NodoBTS<Usuario> anterior = obtenerAnteriorNodo();
            if (anterior != null) {
                mostrarNodo(anterior);
            }
        }
    }

    private NodoBTS<Usuario> obtenerSiguienteNodo() {
        if (nodoActual == null) {
            return null;
        }

        if (!ordenLista) {
            // Recorrido inorden inverso
            if (nodoActual.getLeft() != null) {
                NodoBTS<Usuario> siguiente = nodoActual.getLeft();
                while (siguiente.getRight() != null) {
                    siguiente = siguiente.getRight();
                }
                nodoActual = siguiente;
                return siguiente;
            }
            else {
                NodoBTS<Usuario> ancestro = listaDobleArbol.getRoot();
                NodoBTS<Usuario> sucesor = null;
                while (ancestro != nodoActual) {
                    if (nodoActual.getData().compareTo(ancestro.getData()) > 0) {
                        sucesor = ancestro;
                        ancestro = ancestro.getRight();
                    } else {
                        ancestro = ancestro.getLeft();
                    }
                }
                if (sucesor == null) {
                    NodoBTS<Usuario> primero = listaDobleArbol.getRoot();
                    while (primero.getRight() != null) {
                        primero = primero.getRight();
                    }
                    nodoActual = primero;
                    return primero;
                }
                nodoActual = sucesor;
                return sucesor;
            }
        } else {
            // Recorrido inorden normal
            if (nodoActual.getRight() != null) {
                NodoBTS<Usuario> siguiente = nodoActual.getRight();
                while (siguiente.getLeft() != null) {
                    siguiente = siguiente.getLeft();
                }
                nodoActual = siguiente;
                return siguiente;
            } else {
                NodoBTS<Usuario> ancestro = listaDobleArbol.getRoot();
                NodoBTS<Usuario> sucesor = null;
                while (ancestro != nodoActual) {
                    if (nodoActual.getData().compareTo(ancestro.getData()) < 0) {
                        sucesor = ancestro;
                        ancestro = ancestro.getLeft();
                    } else {
                        ancestro = ancestro.getRight();
                    }
                }
                if (sucesor == null) {
                    NodoBTS<Usuario> primero = listaDobleArbol.getRoot();
                    while (primero.getLeft() != null) {
                        primero = primero.getLeft();
                    }
                    nodoActual = primero;
                    return primero;
                }
                nodoActual = sucesor;
                return sucesor;
            }
        }
    }

    private NodoBTS<Usuario> obtenerAnteriorNodo() {
        if (nodoActual == null) {
            return null;
        }
        if (nodoActual.getLeft() != null) {
            NodoBTS<Usuario> anterior = nodoActual.getLeft();
            while (anterior.getRight() != null) {
                anterior = anterior.getRight();
            }
            nodoActual = anterior;
            return anterior;
        } else {
            NodoBTS<Usuario> ancestro = listaDobleArbol.getRoot();
            NodoBTS<Usuario> predecesor = null;
            while (ancestro != nodoActual) {
                if (nodoActual.getData().compareTo(ancestro.getData()) > 0) {
                    predecesor = ancestro;
                    ancestro = ancestro.getRight();
                } else {
                    ancestro = ancestro.getLeft();
                }
            }
            if (predecesor == null) {
                NodoBTS<Usuario> ultimo = listaDobleArbol.getRoot();
                while (ultimo.getRight() != null) {
                    ultimo = ultimo.getRight();
                }
                nodoActual = ultimo;
                return ultimo;
            }
            nodoActual = predecesor;
            return predecesor;
        }
    }*/

    public void OnTodos(ActionEvent actionEvent) {
        mostrarTodosLosUsuarios();
    }

    private void mostrarTodosLosUsuarios() {
        if (listaDobleArbol != null && !listaDobleArbol.isEmpty()) {
            String usuarios = listaDobleArbol.inOrder();
            outPutArea.clear();
            outPutArea.setText(usuarios);
        }
    }

    private void mostrarNodoActual() {
        if (listaDobleArbol != null && !listaDobleArbol.isEmpty()) {
            NodoBTS<Usuario> actual = obtenerNodoActual();
            if (actual != null) {
                mostrarNodo(actual);
            }
        }
    }

    private void mostrarNodo(NodoBTS<Usuario> nodo) {
        if (nodo != null) {
            outPutArea.clear();
            outPutArea.setText(nodo.getData().toString());
        }
    }
    private void mostrarUsuario(Usuario usuario) {
        // Lógica para mostrar al usuario en la interfaz gráfica
        outPutArea.clear();
        outPutArea.setText(String.valueOf(usuario));
    }


}