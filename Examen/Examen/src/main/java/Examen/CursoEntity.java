/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Examen;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class CursoEntity {
    String nombre;
    double precio;

    public CursoEntity(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public CursoEntity() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
