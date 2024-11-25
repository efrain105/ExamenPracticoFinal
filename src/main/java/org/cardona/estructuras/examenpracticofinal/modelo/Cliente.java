package org.cardona.estructuras.examenpracticofinal.modelo;

import java.util.Objects;

public class Cliente extends Usuario {
    private String direccion;
    private String tipoCliente; // Ejemplo: Regular, Premium

    public Cliente(String telefono, String correo, String nombre, String direccion, String tipoCliente) {
        super(telefono, correo, nombre);
        this.direccion = direccion;
        this.tipoCliente = tipoCliente;
    }

    public Cliente() {
        super();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public String toString() {
        return "Tipo de Usuario: Cliente\n" + super.toString() + "Dirección: " + direccion + "\nTipo de Cliente: " + tipoCliente + "\n";
    }

}
