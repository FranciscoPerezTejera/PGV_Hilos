package pgv_hilos_ud2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedQueueThreadPool {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);
        //Enviamos 5 tareas al pool de hilos
        for (int i = 0; i < 5; i++) {
            int tareadId = i;
            System.out.println("Inicio de la nueva tarea" + tareadId);
            executor.execute(() -> {
                try {
                    
                    Thread.sleep(1000);
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("Tarea " + tareadId + " finalizada");
                
            });
        }
    }
}
