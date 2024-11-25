package org.cardona.estructuras.examenpracticofinal.modelo.enums;

public enum Producto {

    //Productos
    PANTALON("Pantalon"),
    CAMISA("Camisa"),
    ZAPATOS("Zapatos"),
    GORRA("Gorra"),
    GAFAS("Gafas"),
    RELOJ("Reloj"),
    JOYAS("Joyas"),
    CELULAR("Celular"),
    LAPTOP("Laptop"),
    TELEVISION("Television"),
    VIDEOJUEGOS("Videojuegos"),
    JUGUETES("Juguetes"),
    MUEBLES("Muebles"),
    ELECTRODOMESTICOS("Electrodomesticos"),
    HERRAMIENTAS("Herramientas"),
    COMPUTADORAS("Computadoras"),
    IMPRESORAS("Impresoras"),
    ACCESORIOS("Accesorios"),
    OTROS("Otros");

    private final String label;

    Producto(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
