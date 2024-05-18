package com.mercadolibre.shorturl.exception;

/**
 * Clase para manejar una exception personalizada para un error de base de datos.
 *
 * @author Jeimmy Rojas.
 */
public class DatabaseException extends Exception{
	
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseException(String message) {
        super(message);
    }

	public DatabaseException() {
		
	}
}
