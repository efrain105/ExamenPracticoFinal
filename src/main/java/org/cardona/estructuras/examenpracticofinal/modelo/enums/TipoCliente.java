package org.cardona.estructuras.examenpracticofinal.modelo.enums;

public enum TipoCliente {

    NORMAL("Normal"),
    VIP("VIP"),
    EMPRESARIAL("Empresarial"),
    CORPORATIVO("Corporativo");

    private final String label;

    TipoCliente(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
