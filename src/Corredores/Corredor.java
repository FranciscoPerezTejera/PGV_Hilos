package Corredores;

import java.util.Random;

public class Corredor implements Runnable {

    public String nombre;
    public int metrosRecorridos;

    public Corredor(String nombre) {
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
    
    @Override
    public void run() {
         Random random = new Random();
        for (int i = 0; i < 10; i++) { 
            int distanciaAvanzada = random.nextInt(10) + 1;
            metrosRecorridos += distanciaAvanzada;

            int tiempoDescanso = 1000 / (distanciaAvanzada * 10);
            
            try {
                Thread.sleep(tiempoDescanso);
                System.out.println("EL corredor: " + this.getNombre() + " ha recorrido " + this.getMetrosRecorridos() + " metros, está descansando " + tiempoDescanso + " milisegundos");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println(nombre + " ha terminado la carrera.");
    }
}
