package org.cardona.estructuras.examenpracticofinal.modelo;

public class Empleado extends Usuario {
    private String puesto;
    private double salario;

    public Empleado(String telefono, String correo, String nombre, String puesto, double salario) {
        super(telefono, correo, nombre);
        this.puesto = puesto;
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Tipo de Usuario: Empleado\n" + super.toString() + "Puesto: " + puesto + "\nSalario: " + salario + "\n";
    }
}
