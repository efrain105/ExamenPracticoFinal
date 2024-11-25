package org.cardona.estructuras.examenpracticofinal.modelo.enums;

public enum Empresa {

    EMPRESA1("Empresa 1"),
    EMPRESA2("Empresa 2"),
    EMPRESA3("Empresa 3"),
    EMPRESA4("Empresa 4"),
    EMPRESA5("Empresa 5");

    private final String label;

    Empresa(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
