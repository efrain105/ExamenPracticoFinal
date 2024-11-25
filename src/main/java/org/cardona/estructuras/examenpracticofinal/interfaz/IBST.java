package org.cardona.estructuras.examenpracticofinal.interfaz;

public interface IBST<T extends Comparable> {

    void insert(T data);

    boolean exists(T data);

    T obtain(T data);

    boolean isTree();

    boolean isEmpty();

    String preOrder();

    String inOrder();

    String postOrder();

    void delete(T data);

}
