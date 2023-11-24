import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class Ejecucion {

    public static void main(String[] args) {

        int numJugadores = 2;
        int numRondas = 2;

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Timer timer = new Timer();

        // Crear instancias de la clase Juego para cada jugador
        Juego[] jugadores = new Juego[numJugadores];
        for (int i = 0; i < numJugadores; i++) {
            jugadores[i] = new Juego("Jugador " + (i + 1), numRondas, timer);
        }

        // Iniciar el juego para cada jugador
        for (Juego jugador : jugadores) {
            executorService.execute(jugador);
        }

        // Esperar a que todos los jugadores terminen sus partidas
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Detener el temporizador después de que todos los jugadores hayan terminado
        timer.cancel();

        // Mostrar los resultados al finalizar todas las partidas
        for (Juego jugador : jugadores) {
            System.out.println(jugador.getResultados());
        }

        // Determinar al ganador
        int maxPuntos = 0;
        String ganador = "";
        for (Juego jugador : jugadores) {
            if (jugador.getPuntos() > maxPuntos) {
                maxPuntos = jugador.getPuntos();
                ganador = jugador.getNombre();
            }
        }

        System.out.println("\nEl ganador es: " + ganador + " con " + maxPuntos + " puntos.");
    }
}

class Juego implements Runnable {

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
            System.out.println(nombre + " - Ronda " + ronda + ":");

            // Simular lanzamientos y calcular puntos
            for (int lanzamiento = 1; lanzamiento <= 10; lanzamiento++) {
                int resultadoLanzamiento = (int) (Math.random() * 11); // Puntos entre 0 y 10
                puntos += resultadoLanzamiento;
                System.out.println("Lanzamiento " + lanzamiento + ": " + resultadoLanzamiento + " puntos");
            }

            // Programar la ejecución de la tarea del temporizador después de que el jugador haya lanzado sus bolos
            programarTareaTemporizador(ronda);

            try {
                // Esperar un tiempo para dar tiempo al otro jugador antes de pasar a la siguiente ronda
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void programarTareaTemporizador(final int ronda) {
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                System.out.println(nombre + " - Puntos acumulados: " + puntos + "\n");
            }
        };
        // Programar la ejecución de la tarea del temporizador después de que el jugador haya completado todos los lanzamientos
        timer.schedule(tarea, 1000 * ronda); // Programar para ejecutarse después de 1 segundo multiplicado por el número de la ronda
    }

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