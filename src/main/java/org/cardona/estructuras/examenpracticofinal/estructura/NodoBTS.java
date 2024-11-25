package org.cardona.estructuras.examenpracticofinal.estructura;

public class NodoBTS<T extends Comparable<T>> {
    private T data;
    private NodoBTS<T> left;
    private NodoBTS<T> right;

    public NodoBTS(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // Getters y setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodoBTS<T> getLeft() {
        return left;
    }

    public void setLeft(NodoBTS<T> left) {
        this.left = left;
    }

    public NodoBTS<T> getRight() {
        return right;
    }

    public void setRight(NodoBTS<T> right) {
        this.right = right;
    }
}
