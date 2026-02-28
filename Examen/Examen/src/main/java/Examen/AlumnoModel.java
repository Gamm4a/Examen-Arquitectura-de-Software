/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Examen;

import Observer.Publisher;
import Observer.Suscriber;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class AlumnoModel implements Publisher {

    private List<Suscriber> suscriptores;
    private List<CursoEntity> cursosDisponibles;
    private List<CursoEntity> cursosInscritos;
    private double total;

    public AlumnoModel() {
        suscriptores = new LinkedList<>();
        cursosDisponibles = new LinkedList<>();
        cursosInscritos = new LinkedList<>();
        total = 0;
    }

    public boolean buscarCurso(CursoEntity curso) {
        return cursosDisponibles.contains(curso);
    }
    
    public List<CursoEntity> getCursosInscritos(){
        return cursosInscritos;
    }
    
    public void registrarCursos(CursoEntity curso) {
        cursosDisponibles.add(curso);
    }

    public void inscribir(CursoEntity curso) {
        if (cursosDisponibles.contains(curso)) {
            cursosInscritos.add(curso);
            calcularTotal();
        }
    }

    public void calcularTotal() {
        total = 0;
        for (CursoEntity c : cursosInscritos) {
            total += c.getPrecio();
        }
        notificar();
    }

    public double getTotal() {
        return total;
    }

    @Override
    public void suscribir(Suscriber s) {
        suscriptores.add(s);
    }

    @Override
    public void desuscribir(Suscriber s) {
        suscriptores.remove(s);
    }

    @Override
    public void notificar() {
        for (Suscriber s : suscriptores) {
            s.update(this);
        }
    }
}
