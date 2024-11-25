package org.cardona.estructuras.examenpracticofinal.estructura;
import org.cardona.estructuras.examenpracticofinal.interfaz.IBST;

import java.util.ArrayList;
import java.util.List;

public class TDA<T extends Comparable<T>> implements IBST<T> {
    private NodoBTS<T> root;

    public TDA() {
        this.root = null;
    }

    public NodoBTS<T> getRoot() {
        return root;
    }

    public void setRoot(NodoBTS<T> root) {
        this.root = root;
    }

    @Override
    public void insert(T data) {
        if (!existsRecursively(root, data)) {
            root = insertRecursively(root, data);
            balanceTree();
        }
    }

    private NodoBTS<T> insertRecursively(NodoBTS<T> node, T data) {
        if (node == null) {
            return new NodoBTS<>(data);
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(insertRecursively(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(insertRecursively(node.getRight(), data));
        }
        return node;
    }

    // Verificar si un dato existe en el árbol

    @Override
    public boolean exists(T data) {
        return existsRecursively(root, data);
    }

    private boolean existsRecursively(NodoBTS<T> node, T data) {
        if (node == null) {
            return false;
        }
        if (data.compareTo(node.getData()) == 0) {
            return true;
        } else if (data.compareTo(node.getData()) < 0) {
            return existsRecursively(node.getLeft(), data);
        } else {
            return existsRecursively(node.getRight(), data);
        }
    }

    // Obtener un dato específico en el árbol

    @Override
    public T obtain(T data) {
        NodoBTS<T> node = obtainRecursively(root, data);
        return (node != null) ? node.getData() : null;
    }

    private NodoBTS<T> obtainRecursively(NodoBTS<T> node, T data) {
        if (node == null || data.compareTo(node.getData()) == 0) {
            return node;
        }
        if (data.compareTo(node.getData()) < 0) {
            return obtainRecursively(node.getLeft(), data);
        } else {
            return obtainRecursively(node.getRight(), data);
        }
    }

    // Verificar si es un árbol binario de búsqueda válido

    @Override
    public boolean isTree() {
        return isBST(root, null, null);
    }

    private boolean isBST(NodoBTS<T> node, T min, T max) {
        if (node == null) {
            return true;
        }
        if ((min != null && node.getData().compareTo(min) <= 0) ||
                (max != null && node.getData().compareTo(max) >= 0)) {
            return false;
        }
        return isBST(node.getLeft(), min, node.getData()) &&
                isBST(node.getRight(), node.getData(), max);
    }

    // Verificar si el árbol está vacío

    @Override
    public boolean isEmpty() {
        return root == null;
    }
    // Recorrido en preorden

    @Override
    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        preOrderRecursively(root, sb);
        return sb.toString();
    }

    private void preOrderRecursively(NodoBTS<T> node, StringBuilder sb) {
        if (node != null) {
            sb.append(" \n\"+++++++++++++++++++++++++++++\" \n")
                    .append(node.getData()).append(" \n\"+++++++++++++++++++++++++++++\" \n");
            preOrderRecursively(node.getLeft(), sb);
            preOrderRecursively(node.getRight(), sb);
        }
    }


    // Recorrido en inorden
    @Override
    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderRecursively(root, sb);
        return sb.toString();
    }

    private void inOrderRecursively(NodoBTS<T> node, StringBuilder sb) {
        if (node != null) {
            inOrderRecursively(node.getLeft(), sb);
            sb.append(" \n\"+++++++++++++++++++++++++++++\" \n")
                    .append(node.getData()).append(" \n\"+++++++++++++++++++++++++++++\" \n");
            inOrderRecursively(node.getRight(), sb);
        }
    }

    // Recorrido reverse inorden
    public void invertTree() {
        root = invertRecursively(root);
    }

    private NodoBTS<T> invertRecursively(NodoBTS<T> node) {
        if (node == null) {
            return null;
        }

        // Intercambiar los subárboles izquierdo y derecho
        NodoBTS<T> left = invertRecursively(node.getLeft());
        NodoBTS<T> right = invertRecursively(node.getRight());
        node.setLeft(right);
        node.setRight(left);

        return node;
    }


    public void revertTree() {
        root = invertRecursively(root);
    }

    // Recorrido en postorden
    @Override
    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        postOrderRecursively(root, sb);
        return sb.toString();
    }

    private void postOrderRecursively(NodoBTS<T> node, StringBuilder sb) {
        if (node != null) {
            postOrderRecursively(node.getLeft(), sb);
            postOrderRecursively(node.getRight(), sb);
            sb.append(" \n\"+++++++++++++++++++++++++++++\" \n")
                    .append(node.getData()).append(" \n\"+++++++++++++++++++++++++++++\" \n");
        }
    }

    // Eliminar un dato del árbol
    @Override
    public void delete(T data) {
        root = deleteRecursively(root, data);
    }

    private NodoBTS<T> deleteRecursively(NodoBTS<T> node, T data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(deleteRecursively(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(deleteRecursively(node.getRight(), data));
        } else {
            // Caso 1: El nodo no tiene hijos
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            // Caso 2: El nodo tiene solo un hijo
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            // Caso 3: El nodo tiene dos hijos
            T minValue = findMin(node.getRight());
            node.setData(minValue);
            node.setRight(deleteRecursively(node.getRight(), minValue));
        }
        return node;
    }

    // Encontrar el valor mínimo en el subárbol derecho
    private T findMin(NodoBTS<T> node) {
        T minValue = node.getData();
        while (node.getLeft() != null) {
            node = node.getLeft();
            minValue = node.getData();
        }
        return minValue;
    }


    // Método para balancear el árbol
    public void balanceTree() {
        List<NodoBTS<T>> nodes = new ArrayList<>();
        inorderTraversal(root, nodes);  // Paso 1: Recorrido inorden para obtener los nodos ordenados
        this.root = buildBalancedTree(nodes, 0, nodes.size() - 1);  // Paso 2: Construir un árbol balanceado
    }

    // Recorrido inorden para almacenar los nodos
    private void inorderTraversal(NodoBTS<T> node, List<NodoBTS<T>> nodes) {
        if (node != null) {
            inorderTraversal(node.getLeft(), nodes);
            nodes.add(node);  // Agregar el nodo a la lista
            inorderTraversal(node.getRight(), nodes);
        }
    }

    // Construir un árbol balanceado a partir de una lista ordenada de nodos
    private NodoBTS<T> buildBalancedTree(List<NodoBTS<T>> nodes, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;  // Encontrar el nodo central
        NodoBTS<T> node = nodes.get(mid);

        // Reestructurar el árbol con el nodo central como raíz
        node.setLeft(buildBalancedTree(nodes, start, mid - 1));  // Subárbol izquierdo
        node.setRight(buildBalancedTree(nodes, mid + 1, end));   // Subárbol derecho

        return node;
    }

    // Metodo para verificar si el árbol está balanceado
    public boolean isBalanced(NodoBTS<T> node) {
        return checkHeight(node) != -1;
    }

    // Función recursiva para verificar el balanceo del árbol
    private int checkHeight(NodoBTS<T> node) {
        if (node == null) {
            return 0;  // Un árbol vacío tiene altura 0
        }

        // Verificar el balanceo del subárbol izquierdo
        int leftHeight = checkHeight(node.getLeft());
        if (leftHeight == -1) {  // Si el subárbol izquierdo no está balanceado
            return -1;
        }

        // Verificar el balanceo del subárbol derecho
        int rightHeight = checkHeight(node.getRight());
        if (rightHeight == -1) {  // Si el subárbol derecho no está balanceado
            return -1;
        }

        // Verificar la diferencia de altura entre los subárboles
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;  // Si la diferencia es mayor que 1, el árbol no está balanceado
        }

        // Si está balanceado, devolver la altura del subárbol
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Método para vaciar el árbol
    public void clear() {
        root = null;
    }

    public List<T> toOrderList(boolean ordenNormal) {
        List<T> elementos = new ArrayList<>();
        balanceTree(); // Balancear el árbol antes de convertirlo en una lista
        if (ordenNormal) {
            reverseInOrderRecursively(root, elementos);
        } else {
            inOrderRecursively(root, elementos);
        }
        return elementos;
    }

    private void inOrderRecursively(NodoBTS<T> node, List<T> elementos) {
        if (node != null) {
            inOrderRecursively(node.getLeft(), elementos);
            elementos.add(node.getData());
            inOrderRecursively(node.getRight(), elementos);
        }
    }

    private void reverseInOrderRecursively(NodoBTS<T> node, List<T> elementos) {
        if (node != null) {
            reverseInOrderRecursively(node.getRight(), elementos);
            elementos.add(node.getData());
            reverseInOrderRecursively(node.getLeft(), elementos);
        }
    }


}