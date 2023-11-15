package pgv_hilos_ud2;

/*Simmula multiples cajeros automaticos que acceden a una única cuenta bancaria.
Estos cajeros pueden hacer depositos y retiros de la cuenta*/
class Main {

    public static void main(String[] args) {

        CuentaBancaria nuevaCuentaBancaria = new CuentaBancaria("Francisco Perez Tejera", 5000);
        Thread cajeroAutomaticoUno = new Thread(new CajeroAutomaticoMultihilo(nuevaCuentaBancaria, "Cajero Caixa"));
        Thread cajeroAutomaticoDos = new Thread(new CajeroAutomaticoMultihilo(nuevaCuentaBancaria, "Cajero BBVA"));

        cajeroAutomaticoUno.start();
        cajeroAutomaticoDos.start();

    }
}

public class CajeroAutomaticoMultihilo implements Runnable {

    private CuentaBancaria cuenta;
    private String nombre;

    public CajeroAutomaticoMultihilo(CuentaBancaria cuenta, String nombre) {
        this.cuenta = cuenta;
        this.nombre = nombre;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            int cantidad = (int) (Math.random() * 200) + 1;

            if (Math.random() < 0.5) {
                cuenta.meterDinero(cantidad);
            } else {
                cuenta.sacarDinero(cantidad);
            }

            try {
                Thread.sleep((int) (Math.random() * 500));
                System.out.println("Nombre del cajero --> " + this.nombre);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

class CuentaBancaria {

    private int saldo;
    private String nombreCuenta;

    public CuentaBancaria(String nombre, int saldo) {
        this.saldo = saldo;
        this.nombreCuenta = nombre;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombre) {
        this.nombreCuenta = nombre;
    }

    public synchronized void meterDinero(int cantidad) {

        System.out.println("Ingresando dinero a la cuenta " + this.getNombreCuenta() + " la cantidad: " + cantidad + " Euros");
        this.setSaldo(this.saldo + cantidad);
        System.out.println("Saldo ingresado con exito. El saldo actual de la cuenta es: " + this.saldo);

    }

    public synchronized void sacarDinero(int cantidad) {

        if (this.getSaldo() >= cantidad) {

            System.out.println("Retirando dinero de la cuenta " + this.getNombreCuenta() + " la cantidad: " + cantidad + " Euros");
            this.setSaldo(this.saldo - cantidad);
            System.out.println("Saldo retirado con exito. El saldo actual de la cuenta es: " + this.saldo);
        } else {

            System.out.println("No se puede retirar esa cantidad ya que el saldo es inferior, cancelando operación");
        }
    }

}
