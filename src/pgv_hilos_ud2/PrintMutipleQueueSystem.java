package pgv_hilos_ud2;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PrintMutipleQueueSystem {
    public static void main(String[] args) {
        
        Executor printer = Executors.newFixedThreadPool(2);
        
        printer.execute(() -> {
            System.out.println("Imprimiendo documento 1 en impresora A");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Documento 1 impreso en impresora A");
        });
        
        printer.execute(() -> {
            System.out.println("Imprimiendo documento 2 en impresora B");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Documento 2 impreso en impresora B");
        });
        
        printer.execute(() -> {
            System.out.println("Imprimiendo documento 3");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Documento 3 impreso");
        });     
    }
}
