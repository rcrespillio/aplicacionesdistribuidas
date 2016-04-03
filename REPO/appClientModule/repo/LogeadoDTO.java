package repo;

import java.io.Serializable;

public class LogeadoDTO implements Serializable{

	private String apodo;
	private String estado;
	
	
	
	
	public LogeadoDTO(String apodo, String estado) {
		super();
		this.apodo = apodo;
		this.estado = estado;
	}
	public String getApodo() {
		return apodo;
	}
	public void setApodo(String apodo) {
		this.apodo = apodo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
}
