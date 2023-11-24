package EjercicioPracticaPool;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class Juego implements Runnable {

    private String nombre;
    private int numRondas;
    private int puntos;
    private Timer timer;

    public Juego(String nombre, int numRondas, Timer timer) {
        this.nombre = nombre;
        this.numRondas = numRondas;
        this.timer = timer;
    }

    @Override
    public void run() {
        
        for (int ronda = 1; ronda <= numRondas; ronda++) {

            // Simular lanzamientos y calcular puntos
            for (int lanzamiento = 1; lanzamiento <= 10; lanzamiento++) {
                int resultadoLanzamiento = (int) (Math.random() * 11); // Puntos entre 0 y 10
                puntos += resultadoLanzamiento;
                System.out.println(nombre + " lanzamiento " + lanzamiento + ": " + resultadoLanzamiento + " puntos");
            }
            // Programar la ejecución de la tarea del temporizador después de que el jugador haya lanzado sus bolos
           // programarTareaTemporizador(ronda);

            try {
                // Esperar un tiempo para dar tiempo al otro jugador antes de pasar a la siguiente ronda
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*private void programarTareaTemporizador(int ronda) {
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                System.out.println(nombre + " - Puntos acumulados: " + puntos);
            }
        };
        // Programar la ejecución de la tarea del temporizador después de que el jugador haya completado todos los lanzamientos
        timer.schedule(tarea, 1000 * ronda); // Programar para ejecutarse después de 1 segundo
    }*/

    public int getPuntos() {
        return puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getResultados() {
        return nombre + " - Puntos totales: " + puntos;
    }
}
