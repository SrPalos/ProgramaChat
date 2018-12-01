package PruebaSocket;

import java.io.Serializable;

/*
 * Código realizado por
 * César Augusto Palos Sandoval
 * y
 * Alan Fernando Rivera Nava 
 */

public class Paquete implements Serializable {
	public String IpOrigen, IpDestino, Usuario, Mensaje;

	public Paquete() {
	}

	public Paquete(String ipOrigen, String ipDestino, String usuario, String mensaje) {
		super();
		IpOrigen = ipOrigen;
		IpDestino = ipDestino;
		Usuario = usuario;
		Mensaje = mensaje;
	}

	public String getIpOrigen() {
		return IpOrigen;
	}

	public void setIpOrigen(String ipOrigen) {
		IpOrigen = ipOrigen;
	}

	public String getIpDestino() {
		return IpDestino;
	}

	public void setIpDestino(String ipDestino) {
		IpDestino = ipDestino;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getMensaje() {
		return Mensaje;
	}

	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}

}
