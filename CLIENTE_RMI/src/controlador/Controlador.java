package controlador;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.Session;

import org.json.simple.JSONObject;

import repo.LogeadoDTO;
import repo.UsuarioDTO;
import rmi.ConexionServidor;
import rmi_interface.Interface;  //importada del repo


//


public class Controlador implements Interface{

    private static int Puerto;                       //Número del puerto que está alojado el servidor
    private static String IPServer;                  //Dirección IP del servidor, la cual podría utilizarse por defecto el localhost
    private static String nombreReferenciaRemota;    // Nombre del objeto subido
    private static Interface objetoRemoto;
    private static Controlador con;
    private static Logger logger;

   static {
       
        Puerto = 2014;
        IPServer = "localhost";
        nombreReferenciaRemota = "Login";
        establecerConexion();
       
    }

   private Controlador(){};
   
   public static Controlador getControlador(){
	   
	   if(con == null){
		   con = new Controlador();
		 
		   return con;
	   }else{
		  return con;
	   }
   }

   private static Interface establecerConexion(){
	  
	   

	        //Se instancia el objeto que conecta con el servidor
	        ConexionServidor conexion = new ConexionServidor();
	       
	        System.out.println("Inicializar conexion");
	        
	        try {
	            //Se conecta con el servidor
	            if (conexion.iniciarRegistro(IPServer, Puerto, nombreReferenciaRemota)) {
	                //Se obtiene la referencia al objeto remoto
	                objetoRemoto = conexion.getServidor();
	            }  
	             } catch (Exception e) {
	                System.out.println("Ha ocurrido un error..." + e);
	            }
	             
	             return objetoRemoto;
	             
   }
    
    
    public boolean existeUsuario(String email, String pass) throws RemoteException{
    
        boolean resultado = false;
    	Interface objetoRemoto = establecerConexion();
    	
    	if(objetoRemoto!=null){
    		
    	    resultado = objetoRemoto.existeUsuario(email,pass);
           
    	    return resultado;
            }

    return false;
}



	@Override
	public synchronized boolean verificarApodoEnUso(String apodo) throws RemoteException {
		
		boolean resultado = false;
    	//Interface objetoRemoto = establecerConexion();
    	
    	if(objetoRemoto!=null){
    		
    		System.out.println("va a allamar a verificarapodo");
    	    resultado = objetoRemoto.verificarApodoEnUso(apodo);
           
    	    return resultado;
            }

    return false;
	}



	@Override
	public boolean registrarUsuario(String apodo, String email, String pwd) throws RemoteException {
		
		boolean resultado = false;
    	//Interface objetoRemoto = establecerConexion();
    	
    	if(objetoRemoto!=null){
    		
    		System.out.println("va a allamar a registrarUsuario....");
    	    resultado = objetoRemoto.registrarUsuario(apodo, email, pwd);
           
    	    return resultado;
            }

    return false;
	}

	public LogeadoDTO validarYobtenerUsuario(String user, String pwd) throws RemoteException {
		
Interface objetoRemoto = establecerConexion();
    	
    	if(objetoRemoto!=null){
    		
    	    LogeadoDTO usuario = objetoRemoto.validarYobtenerUsuario(user,pwd);
            if(usuario!=null){
    	    return usuario;
            }else{
            	return null;
            }
            }
		
    	return null;
		

   
	}

	

	public synchronized boolean vincularSessionUsuario(String id, String apodo) throws RemoteException {
		
		boolean resultado = false;
Interface objetoRemoto = establecerConexion();
    	
    	if(objetoRemoto!=null){
    		
    	     resultado = objetoRemoto.vincularSessionUsuario(id,apodo);
             if(resultado==true){
            	 return true;
             }else{
            	 return false;
             }
		
    	
		
		
	}

return resultado;
}

	public LogeadoDTO obtenerUsuarioLogeado(String idSession) throws RemoteException {
		
  Interface objetoRemoto = establecerConexion();
    	
    	if(objetoRemoto!=null){
    		
    		
    		
    		LogeadoDTO usuario = objetoRemoto.obtenerUsuarioLogeado(idSession);
            if(usuario!=null){
    	    System.out.println("Controlador del cliente/obtenerusuariologeado exito LOGEADODTO: "+usuario.getApodo()+" "+usuario.getEstado());
            	return usuario;
            }else{
            	System.out.println("Controlador del cliente/obtenerusuariologeado no exito, return null");
            	return null;
            }
    	}  
    	System.out.println("Controlador del cliente/obtenerusuariologeado no exito, return null");
    	return null;
				
	}
}