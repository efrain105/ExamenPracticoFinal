package org.cardona.estructuras.examenpracticofinal.modelo;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Usuario implements Comparable<Usuario> {
    private String telefono; // ID principal
    private String correo;
    private String nombre;

    public Usuario(String telefono, String correo, String nombre) {
        if (telefono.matches("\\d{10}")) { // Validación de número telefónico
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("El número telefónico debe tener exactamente 10 dígitos.");
        }

        if (correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) { // Validación de correo
            this.correo = correo;
        } else {
            throw new IllegalArgumentException("Correo electrónico no válido.");
        }

        this.nombre = nombre;
    }

    public Usuario() {

    }

    public Usuario(String numeroTelefono) {
        this.telefono = numeroTelefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            this.correo = correo;
        } else {
            throw new IllegalArgumentException("Correo electrónico no válido.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Teléfono: " + telefono + "\nCorreo: " + correo + "\nNombre: " + nombre +"\n";
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(telefono, usuario.telefono) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefono, correo, nombre);
    }


    @Override
public int compareTo(@NotNull Usuario o) {
    if (o == null) {
        throw new NullPointerException("El objeto Usuario no puede ser nulo");
    }

    // Compara los números telefónicos (String) numéricamente
    return Long.compare(Long.parseLong(this.telefono), Long.parseLong(o.getTelefono()));
}

}
