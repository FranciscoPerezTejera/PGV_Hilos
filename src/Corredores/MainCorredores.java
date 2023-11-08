package Corredores;

public class MainCorredores {
    
    public static void SimulacionDeCarrera() {
        
        Corredor [] corredores = new Corredor[2];
        Thread [] hilos = new Thread[corredores.length];

        for (int i = 0; i < corredores.length; i++) {
            corredores[i] = new Corredor("Corredor " + (i + 1));
            hilos[i] = new Thread(corredores[i]);
            hilos[i].start();
        }

        for (int i = 0; i < corredores.length; i++) {
            try {
                hilos[i].join(); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        Corredor ganador = corredores[0];
        for (int i = 1; i < corredores.length; i++) {
            if (corredores[i].getMetrosRecorridos() > ganador.getMetrosRecorridos()) {
                ganador = corredores[i];
            }
        }

        System.out.println("El ganador de la carrera es: " + ganador.nombre + " con una distancia de " + ganador.getMetrosRecorridos() + " metros.");
    }
    
    
}
