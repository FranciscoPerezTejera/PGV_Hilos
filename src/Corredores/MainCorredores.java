package Corredores;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class MainCorredores {

    public static void main(String[] argum) {
        
        JFrame frame = new JFrame("Carrera GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));
        frame.setLocationRelativeTo(null);
        JProgressBar [] progressBars = new JProgressBar[2];
        Corredor[] corredores = new Corredor[2];

        // Crear progressBars y corredores
        for (int i = 0; i < corredores.length; i++) {
            progressBars[i] = new JProgressBar(0, 100);
            corredores[i] = new Corredor("Corredor " + (i + 1), progressBars[i]);
            progressBars[i].setValue(corredores[i].getMetrosRecorridos());
            frame.add(progressBars[i]);
        }

        JButton btnIniciarCarrera = new JButton("Iniciar Carrera");
        btnIniciarCarrera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Desactivar el botón para evitar iniciar la carrera múltiples veces
                btnIniciarCarrera.setEnabled(false);

                // Crear hilos para los corredores
                Thread[] hilosCorredores = new Thread[corredores.length];
                
                for (int i = 0; i < corredores.length; i++) {
                    hilosCorredores[i] = new Thread(corredores[i]);
                    hilosCorredores[i].start();
                }

                // Esperar a que todos los corredores terminen
                for (Thread hilo : hilosCorredores) {
                    try {
                        hilo.join();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                // Determinar al ganador
                Corredor ganador = corredores[0];
                for (Corredor corredor : corredores) {
                    if (corredor.getMetrosRecorridos() > ganador.getMetrosRecorridos()) {
                        ganador = corredor;
                    }
                }

                // Mostrar el ganador en un JOptionPane
                JOptionPane.showMessageDialog(frame, "El ganador es: " + ganador.toString() +
                        " con una distancia de " + ganador.getMetrosRecorridos() + " metros.", "Carrera Terminada", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.add(btnIniciarCarrera);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
