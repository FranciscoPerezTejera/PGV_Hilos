package pgv_hilos_ud2;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ColaNoConcurrente implements Runnable {
    
    private static Queue<Integer> cola = new ConcurrentLinkedDeque<>();
    
    @Override
    public void run() {
        cola.add(10);
        
       for (Integer i : cola) {
           System.out.println("*");
       }
        System.out.println("Tamaño cola: " + cola.size());
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new ColaNoConcurrente()).start();
        }
    }
    
}
