
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class Corredor implements Runnable {

    private String nombre;
    private int distanciaRecorrida;
    private JProgressBar progressBar;

    public Corredor(String nombre, JProgressBar progressBar) {
        this.nombre = nombre;
        this.distanciaRecorrida = 0;
        this.progressBar = progressBar;
    }

    public void setDistanciaRecorrida(int distanciaRecorrida) {
        this.distanciaRecorrida = distanciaRecorrida;
    }

    public int getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void run() {
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            // Avanzar una distancia aleatoria entre 1 y 10 metros
            int distanciaAvance = rand.nextInt(10) + 1;
            distanciaRecorrida += distanciaAvance;

            // Actualizar el progreso en la barra utilizando SwingUtilities.invokeLater
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    progressBar.setValue(distanciaRecorrida);
                    progressBar.setString(distanciaRecorrida + " metros");
                }
            });

            // Descansar inversamente proporcional a la velocidad
            try {
                Thread.sleep(1000 / (i + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class CarreraGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Carrera GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        JProgressBar[] progressBars = new JProgressBar[2];
        Corredor[] corredores = new Corredor[2];

        // Crear progressBars y corredores
        for (int i = 0; i < corredores.length; i++) {
            progressBars[i] = new JProgressBar(0, 100);
            corredores[i] = new Corredor("Corredor " + (i + 1), progressBars[i]);
            progressBars[i].setString(corredores[i].getDistanciaRecorrida() + " metros");
            frame.add(progressBars[i]);
        }

        JButton btnIniciarCarrera = new JButton("Iniciar Carrera");
        btnIniciarCarrera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Desactivar el botón para evitar iniciar la carrera múltiples veces
                btnIniciarCarrera.setEnabled(false);

                // Crear hilo para la actualización de la interfaz gráfica
                Thread uiThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
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
                            if (corredor.getDistanciaRecorrida() > ganador.getDistanciaRecorrida()) {
                                ganador = corredor;
                            }
                        }

                        // Mostrar el ganador en un JOptionPane
                        JOptionPane.showMessageDialog(frame, "El ganador es: " + ganador.getNombre()
                                + " con una distancia de " + ganador.getDistanciaRecorrida() + " metros.", "Carrera Terminada", JOptionPane.INFORMATION_MESSAGE);

                        // Activar el botón después de la carrera
                        btnIniciarCarrera.setEnabled(true);
                        btnIniciarCarrera.addActionListener((e) -> {

                            progressBars[0].setValue(0);
                            progressBars[1].setValue(0);
                            corredores[0].setDistanciaRecorrida(0);
                            corredores[1].setDistanciaRecorrida(0);

                        });
                    }
                });

                uiThread.start();
            }
        });

        frame.add(btnIniciarCarrera);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
