package rmi;

import rmi_interface.Interface;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ConexionServidor {

    private Registry registry;  //Registro de la conexi�n del usuario con el servidor
    private boolean conectado;  //Estado de conexi�n del usuario con el servidor
    private Interface servidor; //Interface necesaria para la comunici�n con el objecto del servidor

    public ConexionServidor() {
        this.conectado = false;
        this.registry = null;
        this.servidor = null;
    }

    public boolean iniciarRegistro(String IP, int Puerto, String nombreObjetoRemoto) throws RemoteException {
        try {
            
            //Se le otorga el permiso necesario para poder ejecutar las funciones correspondientes
            java.security.AllPermission allPermision = new java.security.AllPermission();          
            System.setProperty("java.security.policy", "rmi.policy");

            //Se inicia RMI-Registry para el registro del objeto
            try {
                //Obtenemos el Registry del servidor RMI
                registry = LocateRegistry.getRegistry(IP, Puerto);

            //De existir alg�n error con el registro que se desea obtener del servidor, se mostrar� un mensaje
            } catch (RemoteException e) {
                System.out.println(IP + ":" + Puerto);
                System.out.println(e.getMessage());
                System.out.println(e.toString());
            }

            //Vamos al Registry y miramos el Objeto "nombreObjRemoto" para poder usarlo.
            servidor = (Interface) registry.lookup(nombreObjetoRemoto);

            this.conectado = true;
            return true;
            
        //De existir alg�n error con la conexi�n al servidor, se mostrar� un mensaje
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error: No se posee conexi�n");
            
            this.conectado = false;
            return false;
        }

    }
    
    //Getting y Setting de los atributos de ConexionCliente

    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public boolean getConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public Interface getServidor() {
        return servidor;
    }

    public void setServidor(Interface servidor) {
        this.servidor = servidor;
    }
}
