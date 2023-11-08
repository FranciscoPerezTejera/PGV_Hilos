package pgv_hilos_ud2;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PrintCachedQueueSystem {

    public static void main(String[] args) {

        Executor printers = Executors.newSingleThreadExecutor();

        printers.execute(() -> {

            System.out.println("Imprimiendo documento 1");
            try {

                Thread.sleep(2000);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.println("Documento 1 impreso");
        });

        printers.execute(() -> {

            System.out.println("Imprimiendo documento 2");
            try {

                Thread.sleep(1500);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.println("Documento 2 impreso");
          
        });
        
        printers.execute(() -> {

            System.out.println("Imprimiendo documento 3");
            try {

                Thread.sleep(2500);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.println("Documento 3 impreso");
        });

        printers.execute(() -> {

            System.out.println("Imprimiendo documento 4");
            try {

                Thread.sleep(1000);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.println("Documento 4 impreso");
          
        });
    }
}
