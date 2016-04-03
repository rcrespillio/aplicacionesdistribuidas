package rmi_interface;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import repo.LogeadoDTO;
import repo.UsuarioDTO;


//Esta inteface indicará los métodos que están a dispoción del cliente y servidor
//para que puedan interactuar remotamente.
//Todos estos métodos deben poseer como mínimo la excepción RemoteException


public interface Interface extends Remote {
   
     public boolean existeUsuario(String email,String pwd) throws RemoteException;
     
     public boolean verificarApodoEnUso(String apodo) throws RemoteException;
     
     public boolean registrarUsuario(String apodo,String email, String pwd) throws RemoteException;

	public LogeadoDTO validarYobtenerUsuario(String user, String pwd) throws RemoteException;

	public boolean vincularSessionUsuario(String id, String apodo) throws RemoteException;

	public LogeadoDTO obtenerUsuarioLogeado(String idSession) throws RemoteException;
}