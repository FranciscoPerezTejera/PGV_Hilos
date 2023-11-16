package Corredores;

import java.util.Random;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class Corredor implements Runnable {

    private String nombre;
    private int metrosRecorridos;
    private JProgressBar distanciaProgressBar;

    public Corredor(String nombre, JProgressBar progressBar) {
        this.nombre = nombre;
        this.metrosRecorridos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMetrosRecorridos() {
        return metrosRecorridos;
    }

    public void setMetrosRecorridos(int metrosRecorridos) {
        this.metrosRecorridos = metrosRecorridos;
    }

    public JProgressBar getDistanciaProgressBar() {
        return distanciaProgressBar;
    }

    public void setDistanciaProgressBar(JProgressBar distanciaProgressBar) {
        this.distanciaProgressBar = distanciaProgressBar;
    }
    
    @Override
    public void run() {
         Random random = new Random();
        for (int i = 0; i < 10; i++) { 
            int distanciaAvanzada = random.nextInt(10) + 1;
            metrosRecorridos += distanciaAvanzada;

            int tiempoDescanso = 1000 / (i + 1);
             SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    distanciaProgressBar.setValue(metrosRecorridos);
                    distanciaProgressBar.setString(metrosRecorridos + " metros");
                }
            });
            try {
                Thread.sleep(tiempoDescanso);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
