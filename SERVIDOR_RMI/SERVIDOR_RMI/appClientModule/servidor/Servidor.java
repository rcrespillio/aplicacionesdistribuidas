package servidor;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import rmi.InterfazServer;
import rmi.ServidorRMI;



public class Servidor {

    public static ServidorRMI servidor;
    public static int puerto = 2014;
    public static InterfazServer objetoLocal;
    public static String nombreReferenciaRemota = "Login";

    static Logger logger;

    public static void main(String[] args) {
        logger = Logger.getLogger("Servidor");

        //Se inicializa el objeto, el cual podr� ser llamado remotamente
        try {
            objetoLocal = new InterfazServer();
        
          //  java.security.AllPermission allPermision = new java.security.AllPermission();          
           // System.setProperty("java.security.policy", "rmi.policy");
        
        } catch (RemoteException re) {
            //En caso de haber un error, es mostrado por un mensaje
            logger.log(Level.SEVERE, re.getMessage());
        }

        //El objeto se dejer� disponible para una conexi�n remota
        logger.log(Level.INFO, "Se va a conectar...");

        servidor = new ServidorRMI();

        boolean resultadoConexion = servidor.iniciarConexion(objetoLocal, nombreReferenciaRemota, puerto);
        if (resultadoConexion) {
            logger.log(Level.INFO, "Se ha establecido la conexi�n correctamente");
        } else {
            logger.log(Level.INFO, "Ha ocurrido un error al conectarse");
        }

        
        
        
        System.out.println("Presione cualquier tecla y luego Enter para desconectar el servidor...");
        Scanner lector = new Scanner(System.in);
        lector.next();

        //En caso que presione una tecla el administrador, se detiene el servicio
        try {
            servidor.detenerConexion(nombreReferenciaRemota);
        } catch (RemoteException re) {
            //En caso de haber un error, es mostrado por un mensaje
            logger.log(Level.SEVERE, re.getMessage());
        }

        System.exit(0);
    }
}
