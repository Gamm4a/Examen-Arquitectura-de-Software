/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Examen;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class InscripcionController {

    private AlumnoModel alumnoModel;

    public InscripcionController() {
        alumnoModel = new AlumnoModel();
    }

    public void inscribirCurso(CursoEntity curso) {
        alumnoModel.inscribir(curso);
    }

    public boolean buscarCurso(CursoEntity curso) {
        return alumnoModel.buscarCurso(curso);
    }

    public void registrarCursos(CursoEntity curso) {
        alumnoModel.registrarCursos(curso);
    }

    public String generarFichaPago() {
        StringBuilder sb = new StringBuilder("Ficha de pago:\n");
        for (CursoEntity c : alumnoModel.getCursosInscritos()) {
            sb.append(c.getNombre()).append(" - $").append(c.getPrecio()).append("\n");
        }
        sb.append("Total: $").append(alumnoModel.getTotal());
        return sb.toString();
    }

    public AlumnoModel getAlumnoModel() {
        return alumnoModel;
    }
}
