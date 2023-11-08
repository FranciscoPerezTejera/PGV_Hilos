
package pgv_hilos_ud2;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;


public class W10GeneradorNotificaciones{
    public static void mostarMensaje(String mensaje) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "Avisador de inactividad");
        tray.add(trayIcon);
        trayIcon.displayMessage("Aviso de inactividad", mensaje, TrayIcon.MessageType.INFO);
    }
}
