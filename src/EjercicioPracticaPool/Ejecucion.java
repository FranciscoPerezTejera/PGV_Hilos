package EjercicioPracticaPool;


import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Ejecucion {

    public static void main(String[] args) {
        
        int numJugadores = 2;
        int numRondas = 2;

        ExecutorService executorService = Executors.newFixedThreadPool(numJugadores);
        Timer timer = new Timer();

        // Crear instancias de la clase Juego para cada jugador
        Juego [] jugadores = new Juego[numJugadores];
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
        
        //Detenemos el timer
        timer.cancel();

        // Mostrar los resultados al finalizar todas las partidas
        for (Juego jugador : jugadores) {
                System.out.println("\n" + jugador.getResultados());
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
