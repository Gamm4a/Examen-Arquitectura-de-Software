package Examen;

import Observer.Publisher;
import Observer.Suscriber;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FrmInscripcion extends JFrame implements Suscriber {

    JLabel lbl1 = new JLabel("Inscripciones");
    JPanel contenido = new JPanel();

    JPanel disponibles = new JPanel();
    JScrollPane scrollDisponibles = new JScrollPane();
    JPanel seleccionadas = new JPanel();
    JScrollPane scrollSeleccionadas = new JScrollPane();

    JButton botonInsciribir = new JButton("Inscribirse");

    ArrayList<CursoEntity> listaDisponibles = new ArrayList<>();
    ArrayList<CursoEntity> listaSeleccionados = new ArrayList<>();
    InscripcionController controller;

    public FrmInscripcion() {
        controller = new InscripcionController();
        controller.getAlumnoModel().suscribir(this);

        setSize(new Dimension(720, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        contenido.setLayout(new GridLayout(1, 2));

        CursoEntity curso1 = new CursoEntity();
        curso1.nombre = "Matemáticas";
        curso1.precio = 1200.50;

        CursoEntity curso2 = new CursoEntity();
        curso2.nombre = "Física";
        curso2.precio = 1500.00;

        CursoEntity curso3 = new CursoEntity();
        curso3.nombre = "Historia";
        curso3.precio = 900.75;

        controller.registrarCursos(curso1);
        controller.registrarCursos(curso2);
        controller.registrarCursos(curso3);

        listaDisponibles.add(curso1);
        listaDisponibles.add(curso2);
        listaDisponibles.add(curso3);

        listaSeleccionados.add(curso2);

        disponibles.setLayout(new BoxLayout(disponibles, BoxLayout.Y_AXIS));
        seleccionadas.setLayout(new BoxLayout(seleccionadas, BoxLayout.Y_AXIS));

        disponibles.add(new JLabel("Materias disponibles:"));
        seleccionadas.add(new JLabel("Materias seleccionadas:"));

        actualizarListas();

        scrollDisponibles.setViewportView(disponibles);
        scrollSeleccionadas.setViewportView(seleccionadas);

        add(lbl1, BorderLayout.NORTH);

        contenido.add(scrollDisponibles);
        contenido.add(scrollSeleccionadas);

        add(contenido, BorderLayout.CENTER);

        botonInsciribir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inscribir();
            }
        });
        add(botonInsciribir, BorderLayout.SOUTH);

        repaint();
        setVisible(true);
    }

    public void actualizarListas() {
        disponibles.removeAll();
        seleccionadas.removeAll();
        for (int i = 0; i < listaDisponibles.size(); i++) {
            disponibles.add(new PanelCurso(listaDisponibles.get(i)));
        }
        for (int i = 0; i < listaSeleccionados.size(); i++) {
            seleccionadas.add(new PanelCurso(listaSeleccionados.get(i)));
        }
        disponibles.revalidate();
        disponibles.repaint();
        seleccionadas.revalidate();
        seleccionadas.repaint();
    }

    public void inscribir() {
        double total = 0;
        for (CursoEntity curso : listaSeleccionados) {
            total += curso.getPrecio();
            controller.inscribirCurso(curso); // inscribe en el modelo
        }

        String mensaje = "Confirmar inscripción a los cursos; precio total: $" + total;

        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Confirmar inscripción",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            controller.generarFichaPago();// el modelo recalcula y notifica
        } else {
            System.out.println("Cancelar");
        }
    }

    class PanelCurso extends JPanel {

        private CursoEntity curso; // referencia al curso

        public PanelCurso(CursoEntity curso) {
            this.curso = curso; // guardamos el curso
            setLayout(new BorderLayout());
            add(new JLabel(curso.getNombre()), BorderLayout.WEST);
            add(new JLabel("$" + curso.getPrecio()), BorderLayout.EAST);
            setBackground(new Color(131, 131, 131));
            Dimension d = new Dimension(250, 50);
            setSize(d);
            setMaximumSize(d);
            setMinimumSize(d);
            setPreferredSize(d);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cambiar();          // mover curso entre listas
                    actualizarListas(); // refrescar paneles
                }
            });
        }

        void cambiar() {
            if (listaDisponibles.contains(curso)) {
                listaDisponibles.remove(curso);
                listaSeleccionados.add(curso);
            } else if (listaSeleccionados.contains(curso)) {
                listaSeleccionados.remove(curso);
                listaDisponibles.add(curso);
            }
        }
    }

    @Override

    public void update(Publisher p) {
        if (p instanceof AlumnoModel) {
            AlumnoModel model = (AlumnoModel) p;
            double total = model.getTotal();
            actualizarListas();
            JOptionPane.showMessageDialog(this,
                    "Ficha de pago actualizada.\nTotal: $" + total,
                    "Actualización",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
