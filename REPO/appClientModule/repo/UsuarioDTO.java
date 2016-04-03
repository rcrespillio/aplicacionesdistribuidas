package repo;

import java.io.Serializable;



/**
 *
 * @author Usuario
 
 *debe implementar serializable
 *
 *
 *
 */
public class UsuarioDTO implements Serializable{

   
    
    private String apodo;
    private String email;
    private String pass;
    private String estado;
    private String sessionSocket;

    public UsuarioDTO(String apodo, String email, String pass) {
        this.apodo = apodo;
    	this.email = email;
        this.pass = pass;
    }
    
    
    
    
    
    public String getSessionSocket() {
		return sessionSocket;
	}





	public void setSessionSocket(String sessionSocket) {
		this.sessionSocket = sessionSocket;
	}





	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    
    
}
