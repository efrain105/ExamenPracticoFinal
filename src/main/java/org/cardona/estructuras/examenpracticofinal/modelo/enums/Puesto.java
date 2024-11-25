package org.cardona.estructuras.examenpracticofinal.modelo.enums;

public enum Puesto {
    GERENTE("Gerente"),
    SUBGERENTE("Subgerente"),
    VENDEDOR("Vendedor");

    private final String label;

    Puesto(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
