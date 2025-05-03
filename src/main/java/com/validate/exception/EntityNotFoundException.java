package com.validate.exception;

public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = -4632401163523859092L;

	public EntityNotFoundException(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	private String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
