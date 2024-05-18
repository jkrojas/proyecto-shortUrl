package com.mercadolibre.shorturl.exception;

/**
 * Clase para manejar una exception personalizada para un error por NullPointer.
 *
 * @author Jeimmy Rojas.
 */
public class NullPointerException extends Exception{
	
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;

	public NullPointerException(String message) {
        super(message);
    }

	public NullPointerException() {
		
	}
}
