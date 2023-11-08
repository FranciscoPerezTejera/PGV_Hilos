package pgv_hilos_ud2;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PrintQueueSystem {

    public static void main(String[] args) {

        Executor printer = Executors.newSingleThreadExecutor();

        printer.execute(() -> {

            System.out.println("Imprimiendo documento 1");
            try {

                Thread.sleep(2000);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.println("Documento 1 impreso");
        });

        printer.execute(() -> {

            System.out.println("Imprimiendo documento 2");
            try {

                Thread.sleep(1500);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.println("Documento 2 impreso");
          
        });
       
    }

}
