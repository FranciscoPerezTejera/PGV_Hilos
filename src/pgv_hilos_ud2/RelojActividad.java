package pgv_hilos_ud2;

import java.util.Timer;
import java.util.TimerTask;

public class RelojActividad extends TimerTask {

    @Override
    public void run() {

        try {
            W10GeneradorNotificaciones.mostarMensaje("Es momento de andar durante 5 minutos");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Timer temporizador = new Timer();
        temporizador.schedule(new RelojActividad(), 180,180);
    }
}
