package org.cardona.estructuras.examenpracticofinal.modelo;

public class Proveedor extends Usuario {
    private String empresa;
    private String producto;

    public Proveedor(String telefono, String correo, String nombre, String empresa, String producto) {
        super(telefono, correo, nombre);
        this.empresa = empresa;
        this.producto = producto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Tipo de Usuario: Proveedor\n" + super.toString() + "Empresa: " + empresa + "\nProducto: " + producto + "\n";
    }
}
